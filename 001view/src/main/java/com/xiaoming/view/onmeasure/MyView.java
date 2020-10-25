package com.xiaoming.view.onmeasure;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

//https://blog.csdn.net/lovexieyuan520/article/details/50614670

//在android:layout_width和android:layout_height都为match_parent的时候，MyView填满全屏，
// 当我们把android:layout_width和android:layout_height都为wrap_content的时候，我们看到MyView还是填满全屏，
// 当我把android:layout_width和android:layout_height都这是为100dp的时候,看到MyView的大小为100dp了

//结论：
//1、View默认的测量规则是android:layout_width和android:layout_height为match_parent或者wrap_content时，是填充全屏的。
//2、android:layout_width和android:layout_height设置为具体值时，那么是多少，宽高就是多少。
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    //widthMeasureSpec，heightMeasureSpec,父布局给它提供的水平和垂直的空间要求
    //onMeasure方法的作用就是计算出自定义View的宽度和高度。这个计算的过程参照父布局给出的大小，以及自己特点算出结果
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //onMeasure()方法的作用就是测量View需要多大的空间，就是宽和高
        //没有做任何处理，使用View默认的测量规则
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setMeasuredDimension(100, 100);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }
}