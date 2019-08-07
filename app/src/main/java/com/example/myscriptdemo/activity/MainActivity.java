package com.example.myscriptdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myscriptdemo.Coupon;
import com.example.myscriptdemo.MyAdapter;
import com.example.myscriptdemo.callback.OnGrabCouponWithinItem;
import com.example.myscriptdemo.R;
import com.example.myscriptdemo.handler.WeakReferenceHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnGrabCouponWithinItem {
    private static final String tag = "MainActivity";
    private ListView listView;
    private List<Coupon> coupons;
    private MyAdapter adapter;
    public TextView grabCoupon;
    private WeakReferenceHandler handler;

    private static final String[] COUPON_NAMES = {
            "通用红包券","清洁用品红包券","手机优惠券","酒水饮料红包券","个护清洁红包券","生鲜食品优惠券","3C数码红包券","家居用品红包券","坚果零食优惠券","衣服箱包红包券",
    };
    private static final String[] COUPON_VALUES = {"20","25","120","30","50","200","50","60","99","88"};

    Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new WeakReferenceHandler(this);
        initView();
    }

    private void initView(){
        listView = findViewById(R.id.list_view);
        coupons = new ArrayList<>();
        initData();
        adapter = new MyAdapter(this, coupons);
        listView.setAdapter(adapter);
        adapter.setOnGrabCouponWithinItem(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                handler.sendMessage(handler.obtainMessage(0x1, new Coupon("Nike coupon", "50")));
                Toast.makeText(MainActivity.this, coupons.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initData(){
        Coupon coupon;
        for (int i = 0; i < COUPON_NAMES.length; i++){
            coupon = new Coupon(COUPON_NAMES[i], COUPON_VALUES[i]);
            coupons.add(coupon);
        }
    }


    @Override
    public void onGrabCoupon(View v, int position) {
        grabCoupon = v.findViewWithTag(v.getTag());
        grabCoupon.setBackgroundColor(Color.LTGRAY);
        Log.d(tag, "响应的item的position：" + position);
    }

    @Override
    public void onJump(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
