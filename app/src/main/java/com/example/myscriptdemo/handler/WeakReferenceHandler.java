package com.example.myscriptdemo.handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.myscriptdemo.Coupon;
import com.example.myscriptdemo.activity.MainActivity;

import java.lang.ref.WeakReference;

public class WeakReferenceHandler extends Handler {

    private WeakReference<Activity> weakReference;
    public WeakReferenceHandler(Activity activity){
        weakReference = new WeakReference<Activity>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        MainActivity activity = (MainActivity) weakReference.get();
        if (activity != null){
            switch (msg.what){
                case 0x1:
                    Coupon coupon = (Coupon) msg.obj;
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (activity.grabCoupon!= null) {
                        activity.grabCoupon.setText(coupon.getName() + ":" + coupon.getMoney());
                    }else {
                        Toast.makeText(activity, "抢单按钮还未初始化", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    }
}
