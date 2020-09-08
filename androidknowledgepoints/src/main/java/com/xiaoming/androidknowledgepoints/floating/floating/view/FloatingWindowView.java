package com.xiaoming.androidknowledgepoints.floating.floating.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.xiaoming.androidknowledgepoints.R;
import com.xiaoming.androidknowledgepoints.floating.floating.util.TaskUtil;

import android.widget.LinearLayout;
import android.widget.ImageView;

public class FloatingWindowView extends LinearLayout {

    private ImageView search;
    private LinearLayout layout;
    private View view;
    private WindowManager windowManager;
    public static int viewWidth, viewHeight;
    private int startX, startY;
    private int endX, endY;
    private int statusBarHeight;

    private WindowManager.LayoutParams mParams;

    private OnClickListener onClickListener;

    public FloatingWindowView(Context context) {
        super(context);
        initView(context);
    }

    public FloatingWindowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FloatingWindowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        view = LayoutInflater.from(context).inflate(R.layout.layout_floating_window, this);
        layout = (LinearLayout) findViewById(R.id.layout_floating_layout);
        viewWidth = layout.getLayoutParams().width;
        viewHeight = layout.getLayoutParams().height;
        search = (ImageView) findViewById(R.id.img_float_window);

        statusBarHeight = TaskUtil.getStatusBarHeight(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                mParams.x = (int) (event.getRawX() - viewWidth / 2);
                mParams.y = (int) (event.getRawY() - viewHeight / 2 + 25);
                windowManager.updateViewLayout(this, mParams);
                break;
            case MotionEvent.ACTION_UP:
                endX = (int) event.getRawX();
                endY = (int) event.getRawY();
                if ((startX == endX) && (startY == endY)) {
                    onClickListener.onClick(search);
                }
                break;
        }
        return true;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setParams(WindowManager.LayoutParams mParams) {
        this.mParams = mParams;
    }
}
