package com.tencent.tecentim.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtil {
    private static int sScreenHeight;
    private static DisplayMetrics sDisplayMetrics;

    public static int getWindowHeight(Context context) {
        if (sScreenHeight <= 0) {
            WindowManager wm = (WindowManager) (context
                    .getSystemService(Context.WINDOW_SERVICE));
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            sScreenHeight = dm.heightPixels;
        }
        return sScreenHeight;
    }

    public static float dip2px(Context context, float dpValue) {
        final float scale = getDisplayMetrics(context).density;
        return dpValue * scale + 0.5f;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        if (sDisplayMetrics == null) {
            sDisplayMetrics = context.getResources().getDisplayMetrics();
        }
        return sDisplayMetrics;
    }
}
