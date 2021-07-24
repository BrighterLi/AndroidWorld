package com.widget.aaaview.layout.onLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {

    // 子View的水平间隔
    private final static int padding = 20;

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub

        // 动态获取子View实例
        for (int i = 0, size = getChildCount(); i < size; i++) {
            View view = getChildAt(i);
            // 放置子View，宽高都是100
            view.layout(l, t, l + 100, t + 100);
            l += 100 + padding;
        }

    }

}