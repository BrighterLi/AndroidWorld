package com.xiaoming.acrossendwebview.viewpagerswiperconflict;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomViewPager extends ViewPager {

    private boolean isPagingEnabled = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        try {
            return this.isPagingEnabled && super.onInterceptTouchEvent(event);
        }
        catch(IllegalArgumentException exception){
            exception.printStackTrace();
        }
        return false;

    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        return this.isPagingEnabled && super.canScroll(v, checkV, dx, x, y);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
    }


    public Boolean getPagingStatus() {
        return isPagingEnabled;
    }
}
