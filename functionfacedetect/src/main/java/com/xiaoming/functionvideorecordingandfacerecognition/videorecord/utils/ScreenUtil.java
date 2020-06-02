package com.xiaoming.functionvideorecordingandfacerecognition.videorecord.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtil {

    public static int getScreenWidth(Context contex) {
        WindowManager windowManager = (WindowManager) contex.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics(); //创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics); //给白纸设置宽高
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics(); //创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics); //给白纸设置宽高
        return outMetrics.heightPixels;
    }
}
