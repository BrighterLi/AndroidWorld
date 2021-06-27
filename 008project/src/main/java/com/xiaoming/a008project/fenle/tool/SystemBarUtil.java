package com.xiaoming.a008project.fenle.tool;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;


public class SystemBarUtil {

    public static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
    public static final String NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height";
    public static final String NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape";

    private static volatile int sStatusBarHeight = -1;
    private static volatile int sNavigationBarPortraitHeight = -1;
    private static volatile int sNavigationBarLandscapeHeight = -1;


    private SystemBarUtil() {
    }

    public static boolean isStatusBarTintSupport() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getStatusBarHeight(Context context) {
        if (sStatusBarHeight == -1) {
            sStatusBarHeight = getInternalDimensionSize(context.getResources(), STATUS_BAR_HEIGHT_RES_NAME);
        }
        return sStatusBarHeight;
    }


    @TargetApi(14)
    public static int getNavigationBarHeight(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Resources res = context.getResources();
            boolean isInPortrait = (res.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
            if (isInPortrait && sNavigationBarPortraitHeight != -1) {
                return sNavigationBarPortraitHeight;
            }
            if (!isInPortrait && sNavigationBarLandscapeHeight != -1) {
                return sNavigationBarLandscapeHeight;
            }

            String key;
            int result;
            if (isInPortrait) {
                key = NAV_BAR_HEIGHT_RES_NAME;
                result = getInternalDimensionSize(res, key);
                sNavigationBarPortraitHeight = result;
            } else {
                key = NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME;
                result = getInternalDimensionSize(res, key);
                sNavigationBarLandscapeHeight = result;
            }
            return result;
        } else {
            return 0;
        }
    }


}
