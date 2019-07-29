package com.example.myscriptdemo.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;


public class FileHelp {

    public static File getCacheDir(Context context, String dirName){
        String cachePath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
                !Environment.isExternalStorageRemovable()){
            cachePath = context.getExternalCacheDir().getPath();
        }else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + dirName);
    }
}
