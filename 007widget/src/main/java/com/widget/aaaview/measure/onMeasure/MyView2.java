package com.widget.aaaview.measure.onMeasure;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView2 extends View {
    public MyView2(Context context) {
        super(context);
    }

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 500;
        Log.i("MyView2", "bright8#measureWidth#specMode: " +  specMode  + "  #specSize: " + specSize);
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            Log.i("MyView2", "bright8#measureWidth1");
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
            Log.i("MyView2", "bright8#measureWidth2");
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        Log.i("MyView2", "bright8#measureHeight#specMode: " +  specMode  + "  #specSize: " + specSize);
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
            Log.i("MyView2", "bright8#measureHeight1");
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
            Log.i("MyView2", "bright8#measureHeight2");
        }
        return result;
    }

}
