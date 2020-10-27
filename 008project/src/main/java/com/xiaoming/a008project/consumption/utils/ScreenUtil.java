package com.xiaoming.a008project.consumption.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;


public class ScreenUtil {

    private static int sScreenWidth;
    private static int sScreenHeight;
    private static DisplayMetrics sDisplayMetrics;

    private ScreenUtil() {
    }


    public static DisplayMetrics getDisplayMetrics(Context context) {
        if (sDisplayMetrics == null) {
            sDisplayMetrics = context.getResources().getDisplayMetrics();
        }
        return sDisplayMetrics;
    }

    /**
     * reverse dp to px
     */
    public static float dip2px(Context context, float dpValue) {
        final float scale = getDisplayMetrics(context).density;
        return dpValue * scale + 0.5f;
    }

    public static int dip2pxInt(Context context, float dpValue) {
        final float scale = getDisplayMetrics(context).density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * reverse px to dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = getDisplayMetrics(context).density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float px2sp(Context context, float pxValue) {
        return pxValue / getDisplayMetrics(context).scaledDensity;
    }

    public static int sp2px(Context context, float spValue) {
        return (int) (spValue * getDisplayMetrics(context).scaledDensity + 0.5f);
    }

    public static String getScreenResolution(Context context) {
        return getWindowWidth(context) + "*" + getWindowHeight(context);
    }

    public static int getWindowWidth(Context context) {
        if (sScreenWidth <= 0) {
            WindowManager wm = (WindowManager) (context
                    .getSystemService(Context.WINDOW_SERVICE));
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            sScreenWidth = dm.widthPixels;
        }
        return sScreenWidth;
    }

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

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0 && resources.getBoolean(id)) {
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            //获取NavigationBar的高度
            int height = resources.getDimensionPixelSize(resourceId);
            return height;
        } else {
            return 0;
        }
    }

    public static boolean isNavigationBarShow(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = context.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 获取控件在屏幕上的精确高度
     *
     * @param context
     * @param uiDesignScreenWidth 设计图屏幕总宽度
     * @param uiDesignViewHeight  设计图上的控件高度
     * @return
     */
    public static int getViewRealHeight(Context context, float uiDesignScreenWidth, float uiDesignViewHeight) {
        if (context == null || uiDesignScreenWidth == 0) {
            return 0;
        }
        return (int) ((getWindowWidth(context) / uiDesignScreenWidth) * uiDesignViewHeight);
    }


    public static float getDensity(Context context) {
        if (context != null) {
            WindowManager manager = (WindowManager) (context
                    .getSystemService(Context.WINDOW_SERVICE));
            DisplayMetrics metrics = new DisplayMetrics();
            if (manager != null) {
                manager.getDefaultDisplay().getMetrics(metrics);
            }
            return metrics.density;
        }
        return 0;
    }

}
