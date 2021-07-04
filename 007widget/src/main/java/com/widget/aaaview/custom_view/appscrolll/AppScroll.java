package com.widget.aaaview.custom_view.appscrolll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


//二级宫格
public class AppScroll extends RelativeLayout {

    public AppScroll(Context context) {
        this(context, null);
    }

    public AppScroll(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initRv();
    }


    private void initRv() {
    }


}

