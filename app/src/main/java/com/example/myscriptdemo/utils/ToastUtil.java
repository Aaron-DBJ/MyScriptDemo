package com.example.myscriptdemo.utils;

import android.widget.Toast;

import com.example.myscriptdemo.application.MyApplication;

public class ToastUtil {
    public static void showToast(String content){
        Toast.makeText(MyApplication.getAppContext(), content, Toast.LENGTH_SHORT).show();
    }
}
