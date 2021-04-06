package com.xiaoming.androidpoints.floating.timerfloating.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.floating.timerfloating.util.TimeFloatingUtil;

/**
 * Created by brightli on 2020/9/8
 */

//Android悬浮窗的实现--可以置顶，可以设置优先级的view:https://blog.csdn.net/zy_style/article/details/54973934
public class TimeFloatingView extends LinearLayout {
    private WindowManager mWindowManager;
    private LinearLayout mTimeFloatingLayout;
    private View mTimeFloatingView;
    private TextView mTvTime;
    public static int sViewWidth, sViewHeight;
    private WindowManager.LayoutParams mParams;
    public static int sStatusBarHeight;
    private int mCountTime = 30;

    public TimeFloatingView(Context context) {
        super(context);
        initView(context);
    }

    public TimeFloatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TimeFloatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mWindowManager = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        mTimeFloatingView = LayoutInflater.from(context).inflate(R.layout.layout_time_floating_view, this);
        mTimeFloatingLayout = findViewById(R.id.time_floating_layout);
        sViewWidth = mTimeFloatingLayout.getLayoutParams().width;
        sViewHeight = mTimeFloatingLayout.getLayoutParams().height;
        mTvTime = findViewById(R.id.tv_time_count);
        sStatusBarHeight = TimeFloatingUtil.getStatusBarHeight(context);
    }

    public void setParams(WindowManager.LayoutParams params) {
        this.mParams = params;
    }

    public void updateTime() {
        if(mCountTime > 0) {
            mTvTime.setText(--mCountTime + "");
            mTvTime.postInvalidate(); //刷新
        }

    }

}
