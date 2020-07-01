package com.xiaoming.androidknowledgepoints.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class ScreenUtil {

    //获取屏幕宽高
    public static Point getScreenSize(Context context) {
        WindowManager windowManager = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        if(windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            return point;
        }
        return null;
    }

}
