package com.xiaoming.androidknowledgepoints.floating.floating.manager;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.xiaoming.androidknowledgepoints.floating.SecondActivity;
import com.xiaoming.androidknowledgepoints.floating.floating.view.FloatingWindowView;

public class FloatingWindowManager {

    private static FloatingWindowView floatWindowView;
    private static WindowManager.LayoutParams params;
    private static WindowManager windowManager;


    private static WindowManager getWindowManager(Context context) {
        if (windowManager == null) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    public static void createFloatWindow(final Context context) {
        WindowManager windowManager = getWindowManager(context);
        if (floatWindowView == null) {
            floatWindowView = new FloatingWindowView(context);
            if (params == null) {
                params = new WindowManager.LayoutParams();
                params.type = WindowManager.LayoutParams.TYPE_PHONE;
                params.format = PixelFormat.RGBA_8888;
                params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                params.gravity = Gravity.LEFT | Gravity.TOP;
                params.width = FloatingWindowView.viewWidth;
                params.height = FloatingWindowView.viewHeight;
                params.x = 100;
                params.y = 100;
            }
            floatWindowView.setParams(params);
            windowManager.addView(floatWindowView, params);

            floatWindowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SecondActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    public static void removeFloatWindow(Context context) {
        if (floatWindowView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(floatWindowView);
            floatWindowView = null;
        }
    }

    public static boolean isWindowShowing() {
        return floatWindowView != null;
    }
}
