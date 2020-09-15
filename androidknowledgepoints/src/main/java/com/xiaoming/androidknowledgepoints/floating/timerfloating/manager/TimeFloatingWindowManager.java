package com.xiaoming.androidknowledgepoints.floating.timerfloating.manager;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.WindowManager;

import com.xiaoming.androidknowledgepoints.floating.floating.manager.FloatingWindowManager;
import com.xiaoming.androidknowledgepoints.floating.timerfloating.view.TimeFloatingView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by brightli on 2020/9/8
 */
public class TimeFloatingWindowManager {

    private static TimeFloatingView mTimeFloatingView;
    private static WindowManager.LayoutParams mParams;
    private static WindowManager mWindowManager;
    private static Handler mHandler = new Handler();
    private static Timer mTimer = new Timer();

    private static WindowManager getWindowManager(Context context) {
        if(mWindowManager == null) {
            mWindowManager = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        }
        return  mWindowManager;
    }

    public static void createFloatWindow(final Context context) {
        WindowManager windowManager = getWindowManager(context);
        if(mTimeFloatingView == null) {
            mTimeFloatingView = new TimeFloatingView(context);
            if(mParams == null) {
                mParams = new WindowManager.LayoutParams();
                mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                mParams.format = PixelFormat.RGBA_8888;
                mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mParams.gravity = Gravity.RIGHT | Gravity.TOP;
                mParams.width = TimeFloatingView.sViewWidth;
                mParams.height = TimeFloatingView.sViewHeight;
                //mParams.x = 100;
                mParams.y = TimeFloatingView.sStatusBarHeight;
            }
            mTimeFloatingView.setParams(mParams);
            windowManager.addView(mTimeFloatingView, mParams);
            startTime();
        }
    }

    public static void removeFloatWindow(Context context) {
        if (mTimeFloatingView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(mTimeFloatingView);
            mTimeFloatingView = null;
        }
    }

    public static void startTime() {
        mTimer.schedule(mTask, 1000, 1000);
    }

    static TimerTask mTask = new TimerTask() {
        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mTimeFloatingView.updateTime();
                }
            });
        }
    };

}
