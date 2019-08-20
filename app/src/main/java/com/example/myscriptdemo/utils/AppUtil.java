package com.example.myscriptdemo.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class AppUtil {
    public static boolean isDebug(Context context){
        if (context == null){
            return false;
        }
        return (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

}
