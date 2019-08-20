package com.example.myscriptdemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myscriptdemo.R;

public class UrlSchemaActivity extends AppCompatActivity{
    WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schema);
        init();
//        initWebView();
    }

    private void init(){
        mWebView = findViewById(R.id.web_view);
    }

    private void initWebView(){
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = Uri.parse("banma://cs/waybill");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (uri.getScheme().equals("banma")){
                   intent.setData(uri);
                }
                startActivity(intent);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }
}
