package com.xiaoming.a008project.fenle.home.header;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiaoming.a008project.R;
import com.xiaoming.a008project.consumption.view.MyTabStrip;
import com.xiaoming.a008project.fenle.home.HomeActivity;
import com.xiaoming.a008project.fenle.tool.SystemBarUtil;

public class CommHeader extends  LinearLayout{
    protected View mVStatusBar;


    public CommHeader(Context context) {
        super(context);
    }

    public CommHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CommHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void bind(HomeActivity homeActivity) {
        //mVStatusBar = findViewById(R.id.mVStatusBar);


        // 兼容沉浸式
        /*if (SystemBarUtil.isStatusBarTintSupport()) {
            mVStatusBar.setLayoutParams(new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    SystemBarUtil.getStatusBarHeight(getContext())));
        }*/
    }

}
