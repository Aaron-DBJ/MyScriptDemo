package com.example.myscriptdemo.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.myscriptdemo.application.MyApplication;

public class CallNativeObject {
    private Context context = MyApplication.getAppContext();

    @JavascriptInterface
    public void showToast(String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void jump(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("banma://cs/waybill"));
//        intent.setComponent(new ComponentName("com.example.myscriptdemo.activity", "com.example.myscriptdemo.activity.SecondActivity"));
        context.startActivity(intent);
    }
}
