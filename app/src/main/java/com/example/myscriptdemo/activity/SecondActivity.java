package com.example.myscriptdemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myscriptdemo.Coupon;
import com.example.myscriptdemo.R;
import com.example.myscriptdemo.handler.LeakHandler;
import com.example.myscriptdemo.web.CallNativeObject;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    public TextView testInfo;
    private LeakHandler leakHandler;
    private Button sendMessage;
    private Coupon coupon;
    private WebView webView;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x2:
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Coupon coupon = (Coupon) msg.obj;
                    testInfo.setText(coupon.getName() + ":" + coupon.getMoney());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initWebView();
//        testInfo = findViewById(R.id.test_info);
        sendMessage = findViewById(R.id.send_message);
        leakHandler = new LeakHandler(this);
        sendMessage.setOnClickListener(this);
        coupon =  new Coupon("Apple优惠券","200");
//        Message message = Message.obtain();
//        message.what = 0x2;
//        message.obj = coupon;
//        handler.sendMessageDelayed(message, 10000);
        webView.loadUrl("file:///android_asset/test.html");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10000);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("banma://cs:8888/waybill"));
//        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
//        Message message = Message.obtain();
//        message.what = 0x2;
//        message.obj = coupon;
//        handler.sendMessageDelayed(message, 10000);
//        finish();

        webView.loadUrl("javascript:callFromNative('JS代码调用成功啦！')");
    }

    private void initWebView(){
        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new CallNativeObject(), "TestJS");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = Uri.parse("banma://cs:8888/waybill");
                if (uri.getScheme().equals("banma")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
//                return super.shouldOverrideUrlLoading(view, request);
                return true;
            }
        });
    }

}
