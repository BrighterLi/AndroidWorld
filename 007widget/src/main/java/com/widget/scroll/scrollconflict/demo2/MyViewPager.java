package com.widget.scroll.scrollconflict.demo2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyViewPager extends ViewGroup {

    private int lastX; //记录手指上一次按下的X坐标
    private int lastY; //记录手指上一次按下的Y坐标

    private int maxValue;   //滑动的最大值
    private int minValue = 0;   //滑动的最小值

    private int width;  //ScrollLayout的宽度

    private Scroller mScroller;

    public MyViewPager(Context context) {
        super(context);
        init();
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (mScroller == null) {
            mScroller = new Scroller(getContext()); //获取Scroller实例
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取ScrollLayout尺寸的建议值和模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //子元素最大高度
        int maxHeight = 0;

        //获取子元素个数
        int childNumber = getChildCount();
        //遍历测量子元素，并找到高度的最大值
        for (int i = 0; i < childNumber; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childHeight = child.getMeasuredHeight();
            maxHeight = childHeight > maxHeight ? childHeight : maxHeight;  //刷新最大高度
        }

        //容器高度等于子元素最大高度加上下边距
        int height = maxHeight + getPaddingTop() + getPaddingTop();
        //确定ScrollLayout自己尺寸，宽度采用建议值，高度分情况讨论
        setMeasuredDimension(widthSize, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //Log.d("MyViewPager", "onLayout执行");
        //位置指针，记录从哪里开始摆放子元素
        int leftCur = 0;
        //获取子元素个数
        int childNumber = getChildCount();
        for (int i = 0; i < childNumber; i++) {
            View child = getChildAt(i);
            //确定子元素位置
            child.layout(leftCur, getPaddingTop(), leftCur + child.getMeasuredWidth(), getPaddingTop() + child.getMeasuredHeight());
            //更新指针位置
            leftCur += child.getMeasuredWidth();
        }

        width = getWidth();
        maxValue = (getChildCount() - 1) * width;   //确定指针最大值
        //Log.d("hahaha", "maxValue =  " + maxValue);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyViewPager", "父容器onTouchEvent执行");
        int x = (int) event.getRawX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;  //计算手指滑动的举例
                if (getScrollX() - deltaX < minValue) {
                    //如果左越界则强制滑动到左边界
                    scrollTo(minValue, 0);
                } else if (getScrollX() - deltaX > maxValue) {
                    //如果右越界则强制滑动到右边界
                    scrollTo(maxValue, 0);
                } else {
                    //没越界正常滑动
                    scrollBy(-deltaX, 0);
                }
                break;

            case MotionEvent.ACTION_UP:
                float floatValue = (float) getScrollX() / width;
                int intValue = getScrollX() / width;
                if (floatValue - intValue > 0.5) {
                    smoothScrollTo((intValue + 1) * width, 0);
                } else {
                    smoothScrollTo(intValue * width, 0);
                }
                break;
        }
        lastX = x;
        return true;
    }

    //外部拦截法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("MyViewPager", "父容器onInterceptTouchEvent执行");
        boolean intercepted = false;
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false; //ACTION_DOWN父容器要返回false
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = Math.abs(x - lastX);
                int deltaY = Math.abs(y - lastY);
                if (deltaX >= deltaY) { //左右滑动则拦截，即父View自己处理
                    intercepted = true;
                } else { //上下滑动
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false; //ACTION_UP父容器要返回false
                break;
        }
        lastX = x;
        lastY = y;
        return intercepted;
    }

    //内部拦截法
   /* @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();

        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            lastX = x;
            lastY = y;
            return false; //内部拦截法，需要设置ACTION_DOWN，父容器不拦截
        } else {
            return true;
        }
    }*/

    private void smoothScrollTo(int destx, int desty) {
        int scrollX = getScrollX();
        int deltaX = destx - scrollX;
        mScroller.startScroll(scrollX, 0, deltaX, 0, 800);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

}
