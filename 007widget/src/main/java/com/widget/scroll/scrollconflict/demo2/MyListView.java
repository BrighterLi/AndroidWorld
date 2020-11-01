package com.widget.scroll.scrollconflict.demo2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;


public class MyListView extends ListView {

    private int lastX, lastY;

    boolean flag = false;

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //内部拦截法
   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("MyListView", "ListView的dispatchTouchEvent执行");
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true); //这里得设置父View不能够拦截
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!flag) {
                    int deltaX = Math.abs(x - lastX);
                    int deltaY = Math.abs(y - lastY);
                    if (deltaX > deltaY) { //左右滑动父容器处理
                        //交给父容器处理
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else { //上下滑动自己处理
                        flag = true;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                flag = false;
                break;
            }
            default:
                break;
        }
        lastX = x;
        lastY = y;
        return super.dispatchTouchEvent(ev);
    }*/


}
