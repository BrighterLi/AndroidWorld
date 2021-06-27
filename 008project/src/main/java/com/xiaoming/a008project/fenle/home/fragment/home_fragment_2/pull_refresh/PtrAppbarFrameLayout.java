package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh.AppBarStateChangeListener;
import com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh.InnerViewScrollState;

import in.srain.cube.views.wt.PtrFrameLayout;

/**
 * Desc: 嵌套AppbarLayout 的下拉刷新, 关联 AppBarLayout 展开状态
 */

public class PtrAppbarFrameLayout extends PtrFrameLayout {
    private boolean isDealTopBorder;

    private float mDownX;
    private float mDownY;

    private AppBarStateChangeListener.State mCurrentState = null;
    private InnerViewScrollState innerViewScrollState = null;
    private int mTouchSlop = 6;

    public PtrAppbarFrameLayout(Context context) {
        this(context, null);
    }

    public PtrAppbarFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrAppbarFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    public void setAppbarLayoutState(AppBarStateChangeListener.State state) {
        this.mCurrentState = state;
    }

    public void setInnerViewScrollState(InnerViewScrollState innerViewScrollState) {
        this.innerViewScrollState = innerViewScrollState;
    }

    private MotionEvent down;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //将按下时的坐标存储
                mDownX = event.getX();
                mDownY = event.getY();
                isDealTopBorder = true;
                down = null;
                setEnabled(false);
                break;
            case MotionEvent.ACTION_MOVE:
                //获取到距离差
                float dx = event.getX() - mDownX;
                float dy = event.getY() - mDownY;
                //通过距离差判断方向
                int orientation = getOrientation(dx, dy);
                Log.i("调试滑动", "ptr move ");
                switch (orientation) {
                    //左右滑动交给子view处理
                    case 'l':
                    case 'r':
                        break;
                    case 't':
                        if (mPtrIndicator.getCurrentPosY() > 0) {
                            if (!isEnabled()) {
                                startByEvent(event);
                            }
                            setEnabled(true);
                        } else {
                            setEnabled(false);
                        }
                        break;
                    case 'b':
                        if (AppBarStateChangeListener.State.EXPANDED == mCurrentState ||
                                (AppBarStateChangeListener.State.GONE == mCurrentState && (innerViewScrollState == null || innerViewScrollState.scrollToTop()))) {
                            if (!isEnabled()) {
                                startByEvent(event);
                                setEnabled(true);
                                if (isDealTopBorder) {
                                    isDealTopBorder = false;
                                    MotionEvent cancel = MotionEvent.obtain(event);
                                    cancel.setAction(MotionEvent.ACTION_CANCEL);
                                    super.dispatchTouchEvent(cancel);

                                    MotionEvent down = MotionEvent.obtain(event);
                                    down.setAction(MotionEvent.ACTION_DOWN);
                                    down = event;
                                    return super.dispatchTouchEvent(down);
                                }

                            }
                        } else {
                            setEnabled(false);
                        }
                        break;
                    default:
                        break;
                }
                break;
        }

        return super.dispatchTouchEvent(event);
    }

//    @SuppressLint("ClickableViewAccessibility")
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        boolean result = super.onTouchEvent(event);
//        if (isRefreshing()) {
//            return true;
//        }
//        return result;
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
//        if (down != null && event == down) {
//            super.onInterceptTouchEvent(event);
//            return true;
//        }
        boolean result = super.onInterceptTouchEvent(event);
        Log.i("调试滑动", "ptr intercept " + result + " : " + event);
        return result;
    }

    private int getOrientation(float dx, float dy) {
        if (Math.abs(dx) < mTouchSlop && Math.abs(dy) < mTouchSlop) {
            return 0;
        }

        if (Math.abs(dx) > Math.abs(dy)) {
            //X轴移动
            return dx > 0 ? 'r' : 'l';//右,左
        } else {
            //Y轴移动
            return dy > 0 ? 'b' : 't';//下,上
        }
    }
}
