package com.example.myscriptdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static SimpleDateFormat dateFormat;
    public static String getDate_yyyyMMdd(){
        return getDate_yyyyMMdd("yyyy-MM-dd");
    }

    public static String getDate_yyyyMMdd_HH_mm_ss(){
        return getDate_yyyyMMdd("yyyy-MM-dd HH:mm:ss");
    }

    private static String getDate_yyyyMMdd(String pattern){
        dateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        Date date = new Date();
        return dateFormat.format(date);
    }


}
