package com.example.myscriptdemo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.io.File;
import java.io.InputStream;

public class BitmapUtil {
    private static BitmapUtil instance;
    private Context context;
//    private static final int FROM_BYTEARRAY = 0;
//    private static final int FROM_FILE = 1;
//    private static final int FROM_FILE_DESCRIPTOR = 2;
//    private static final int FROM_SRTEAM = 3;
//    private static final int FROM_RESOURCE = 4;

    private BitmapUtil(Context context){
        this.context = context;
    }

    public static BitmapUtil getInstance(Context context){
        if (instance == null){
            synchronized (BitmapUtil.class){
                if (instance == null){
                    instance = new BitmapUtil(context);
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

    private Bitmap decodeInSampledBitmapFromResource(int resId,  int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    private Bitmap decodeInSampledBitmapFromStream(InputStream inputStream, int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, new Rect(0,0,0,0), options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream,new Rect(0,0,0,0), options);
    }

    private Bitmap decodeInSampledBitmapFromFile(String file, int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file, options);
    }

    private Bitmap compressPixelByte(String file, Bitmap.Config config){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        return BitmapFactory.decodeFile(file, options);
    }


}
