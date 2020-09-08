package com.xiaoming.androidknowledgepoints.floating.timerfloating.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xiaoming.androidknowledgepoints.R;

/**
 * Created by brightli on 2020/9/8
 */
public class TimeFloatingView extends LinearLayout {
    private WindowManager mWindowManager;
    private LinearLayout mTimeFloatingLayout;
    private View mTimeFloatingView;

    public TimeFloatingView(Context context) {
        super(context);
    }

    public TimeFloatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    
    private void initView(Context context) {
        mWindowManager = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        mTimeFloatingView = LayoutInflater.from(context).inflate(R.layout.layout_time_floating_view, this);
        mTimeFloatingLayout = findViewById(R.id.layout_floating_layout);
    }
}
