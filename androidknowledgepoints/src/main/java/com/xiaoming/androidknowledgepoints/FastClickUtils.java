package com.xiaoming.androidknowledgepoints;

//防止快速点击，全局
public class FastClickUtils {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long currentClickTime = System.currentTimeMillis();
        if((currentClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME) {
            //快速点击
            flag = true;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
