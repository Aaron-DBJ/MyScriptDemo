package com.example.myscriptdemo;

import android.app.Application;

import com.example.myscriptdemo.crash.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

import javax.security.auth.callback.Callback;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        // 初始化LeakCanary
        LeakCanary.install(this);
        // 设置全局异常捕获器
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
