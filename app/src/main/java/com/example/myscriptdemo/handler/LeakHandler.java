package com.example.myscriptdemo.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.myscriptdemo.Coupon;
import com.example.myscriptdemo.activity.SecondActivity;

public class LeakHandler extends Handler {
    private Context context;
    public LeakHandler(Context context){
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        SecondActivity secondActivity = (SecondActivity) context;
        if (secondActivity != null){
            switch (msg.what){
                case 0x2:
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Coupon coupon = (Coupon) msg.obj;
                    secondActivity.testInfo.setText(coupon.getName() + ":" + coupon.getMoney());
                    break;
            }
        }

    }
}
