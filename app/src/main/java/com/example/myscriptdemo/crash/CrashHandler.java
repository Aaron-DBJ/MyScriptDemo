package com.example.myscriptdemo.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;

import com.example.myscriptdemo.utils.DateUtil;
import com.example.myscriptdemo.utils.FileHelp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private CrashHandler(){

    }

    public static CrashHandler getInstance(){
        if (instance == null){
            synchronized (CrashHandler.class){
                if (instance == null){
                    instance = new CrashHandler();
                }
            }
        }
        return instance;
    }

    public void init(Context context){
        mContext = context;
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            dumpCrashInfoToSDCard(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        dumpCrashInfoToServer();

        // 发生crash之后，需要将进程杀掉，因为此时程序不能继续往下运行，程序状态已不对
        // 如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (mDefaultExceptionHandler != null){
            mDefaultExceptionHandler.uncaughtException(t, e);
        }else {
            Process.killProcess(Process.myPid());
        }
    }

    private void dumpCrashInfoToSDCard(Throwable ex) throws Exception {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            throw new Exception("没有SD卡，无法写入到本地");
        }
        File dir = FileHelp.getCacheDir(mContext, "crash");
        if (!dir.exists()){
            dir.mkdirs();
        }

        File crashInfo = new File(dir, "crashInfo" + DateUtil.getDate_yyyyMMdd()+".txt");
        PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(crashInfo)));
        printWriter.println(DateUtil.getDate_yyyyMMdd_HH_mm_ss());
        dumpPhoneInfo(printWriter);
        printWriter.println();
        ex.printStackTrace(printWriter);
        printWriter.close();
    }

    private void dumpCrashInfoToServer(){
        // todo
    }

    private void dumpPhoneInfo(PrintWriter writer) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        // app 版本
        writer.print("APP Version:" + packageInfo.versionName);
        writer.print("_" + packageInfo.versionCode);
        writer.println();
        // OS版本
        writer.print("OS Version:");
        writer.print(Build.VERSION.RELEASE);
        writer.println("_" + Build.VERSION.SDK_INT);
        // 手机制造商
        writer.print("Vendor: ");
        writer.println(Build.MANUFACTURER);
        // 手机型号
        writer.print("Model:");
        writer.println(Build.MODEL);
        // 手机CPU架构
        writer.print("CPU ABI:");
        writer.println(Build.CPU_ABI);
    }


}
