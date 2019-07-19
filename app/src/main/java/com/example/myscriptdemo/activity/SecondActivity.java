package com.example.myscriptdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myscriptdemo.Coupon;
import com.example.myscriptdemo.R;
import com.example.myscriptdemo.handler.LeakHandler;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    public TextView testInfo;
    private LeakHandler leakHandler;
    private Button sendMessage;
    private Coupon coupon;
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
        testInfo = findViewById(R.id.test_info);
        sendMessage = findViewById(R.id.send_message);
        leakHandler = new LeakHandler(this);
        sendMessage.setOnClickListener(this);
        coupon =  new Coupon("Apple优惠券","200");
//        Message message = Message.obtain();
//        message.what = 0x2;
//        message.obj = coupon;
//        handler.sendMessageDelayed(message, 10000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10000);

    }

    @Override
    public void onClick(View v) {
//        Message message = Message.obtain();
//        message.what = 0x2;
//        message.obj = coupon;
//        handler.sendMessageDelayed(message, 10000);
        finish();
    }

}
