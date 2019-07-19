package com.example.myscriptdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myscriptdemo.callback.OnGrabCouponWithinItem;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Coupon> coupons;
    private Context context;
    private OnGrabCouponWithinItem onGrabCouponWithinItem;
    private static final int HEAD_VIEW_TYPE = 0;
    private static final int COMMON_VIEW_TYPE = 1;


    public MyAdapter(Context context, List<Coupon> coupons){
        this.context = context;
        this.coupons = coupons;
    }

    public void setOnGrabCouponWithinItem(OnGrabCouponWithinItem onGrabCouponWithinItem){
        this.onGrabCouponWithinItem = onGrabCouponWithinItem;
    }

    @Override
    public int getCount() {
        return coupons.size();
    }

    @Override
    public Object getItem(int position) {
        return coupons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        int type = getItemViewType(position);
        ViewHolder holder = null;
        HeadViewHolder headHolder = null;
        if (convertView == null){
            switch (type){
                case COMMON_VIEW_TYPE:
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_coupon, null);
                    holder.couponInfo = convertView.findViewById(R.id.coupon_info);
                    holder.couponValue = convertView.findViewById(R.id.money);
                    holder.grabCoupon = convertView.findViewById(R.id.grab_coupon);
                    convertView.setTag(holder);
                    break;
                case HEAD_VIEW_TYPE:
                    headHolder = new HeadViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_header_view, null);
                    headHolder.jump = convertView.findViewById(R.id.head_button);
                    convertView.setTag(headHolder);
                    break;
            }
        }else {
            switch (type){
                case COMMON_VIEW_TYPE:
                    holder = (ViewHolder) convertView.getTag();
                    break;
                case HEAD_VIEW_TYPE:
                    headHolder = (HeadViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (type){
            case COMMON_VIEW_TYPE:
                holder.couponInfo.setText(coupons.get(position).getName());
                holder.couponValue.setText(coupons.get(position).getMoney());
                holder.grabCoupon.setTag(coupons.get(position).getName());
                holder.grabCoupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onGrabCouponWithinItem.onGrabCoupon(view, position);
                    }
                });
                break;
            case HEAD_VIEW_TYPE:
                headHolder.jump.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onGrabCouponWithinItem.onJump(v);
                    }
                });
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return HEAD_VIEW_TYPE;
        }
        return COMMON_VIEW_TYPE;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    //    @Override
//    public void onClick(View view) {
//        onGrabCouponWithinItem.onGrabCoupon(view);
//    }


    class ViewHolder{
        TextView couponInfo;
        TextView couponValue;
        TextView grabCoupon;
    }

    class HeadViewHolder{
        Button jump;
    }
}
