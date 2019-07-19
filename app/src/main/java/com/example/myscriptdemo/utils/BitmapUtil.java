package com.example.myscriptdemo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
    private static BitmapUtil instance;
    private static final int FROM_BYTEARRAY = 0;
    private static final int FROM_FILE = 1;
    private static final int FROM_FILE_DESCRIPTOR = 2;
    private static final int FROM_SRTEAM = 3;
    private static final int FROM_RESOURCE = 4;

    private BitmapUtil(){

    }

    public static BitmapUtil getInstance(){
        if (instance == null){
            synchronized (BitmapUtil.class){
                if (instance == null){
                    instance = new BitmapUtil();
                }
            }
        }
        return instance;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;
        int inSampleSize = 1;
        if (reqWidth<originalWidth || reqHeight < originalHeight){
            int widthRatio = originalWidth/reqWidth;
            int heightRation = originalHeight/reqHeight;
            inSampleSize = widthRatio > heightRation ? heightRation:widthRatio;
        }
        return inSampleSize;
    }

    private Bitmap decodeInSampledBitmap(int from){
        switch (from){
            case FROM_BYTEARRAY:

        }
    }
    private void decodeInSampledBitmap(BitmapFactory.Options options, int reqWidth, int reqHeight){
        BitmapFactory.
    }


}
