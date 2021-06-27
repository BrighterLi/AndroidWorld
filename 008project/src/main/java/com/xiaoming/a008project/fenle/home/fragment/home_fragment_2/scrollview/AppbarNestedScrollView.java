package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh.AppBarStateChangeListener;
import com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh.InnerViewScrollState;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class AppbarNestedScrollView extends NestedScrollView {
    private static final String TAG = "AppbarNestedScrollView";
    private boolean isDealTopBorder;
    private float mDownX;
    private float mDownY;

    private AppBarStateChangeListener.State mCurrentState = null;
    private InnerViewScrollState innerViewScrollState = null;
    private int mTouchSlop = 6;

    public AppbarNestedScrollView(@NonNull Context context) {
        this(context, null);
    }

    public AppbarNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppbarNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
                break;
            case MotionEvent.ACTION_MOVE:
                //获取到距离差
                float dx = event.getX() - mDownX;
                float dy = event.getY() - mDownY;
                //通过距离差判断方向
                int orientation = getOrientation(dx, dy);
                switch (orientation) {
                    //左右滑动交给子view处理
                    case 'l':
                    case 'r':
                        break;
                    case 't':
                        if (mCurrentState == AppBarStateChangeListener.State.COLLAPSED || mCurrentState == AppBarStateChangeListener.State.GONE) {
                            //闭合状态，需要先到顶再拦截和 AppBarLayout 滑动联动，
                            if (innerViewScrollState == null || innerViewScrollState.scrollToTop()) {
                                Log.i(TAG, "dispatchTouchEvent move b top");
                                if (isDealTopBorder) {
                                    isDealTopBorder = false;
                                    event.setAction(MotionEvent.ACTION_CANCEL);
                                    super.dispatchTouchEvent(event);

                                    event.setAction(MotionEvent.ACTION_DOWN);
                                    return super.dispatchTouchEvent(event);
                                }
                            }
                        }
                        break;
                    case 'b':
                        if (mCurrentState == AppBarStateChangeListener.State.COLLAPSED || mCurrentState == AppBarStateChangeListener.State.GONE) {
                            //闭合状态，需要先到顶再拦截和 AppBarLayout 滑动联动，
                            if (innerViewScrollState == null || innerViewScrollState.scrollToTop()) {
                                Log.i(TAG, "dispatchTouchEvent move b top");
                                if (isDealTopBorder) {
                                    isDealTopBorder = false;
                                    event.setAction(MotionEvent.ACTION_CANCEL);
                                    super.dispatchTouchEvent(event);

                                    event.setAction(MotionEvent.ACTION_DOWN);
                                    //这个down 传递到子控件会触发子控件的长按监听，所以做了直接拦截处理
                                    down = event;
                                    return super.dispatchTouchEvent(event);
                                }
                            }
                        }

                    default:
                        break;
                }
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //将按下时的坐标存储
                mDownX = event.getX();
                mDownY = event.getY();
                Log.i("调试滑动", "down " + mCurrentState);
                if (down != null && event == down) {
                    super.onInterceptTouchEvent(event);
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //获取到距离差
                float dx = event.getX() - mDownX;
                float dy = event.getY() - mDownY;
                //通过距离差判断方向
                int orientation = getOrientation(dx, dy);
                Log.i("调试滑动", "orientation " + orientation  +" : " + mCurrentState);
                switch (orientation) {
                    //左右滑动交给子view处理
                    case 'l':
                    case 'r':
                        //左右滑动始终不拦截，内部控件可以自由滑动,本身不滑动
                        break;
                    case 't':
                        //没有闭合状态，不管是上滑还是下滑都需要拦截, NestedScrollView 本身滑动，内部控件不能滑动
                        if (mCurrentState != AppBarStateChangeListener.State.COLLAPSED && mCurrentState != AppBarStateChangeListener.State.GONE) {
                            Log.i("调试滑动", "scrollview 拦截" + mCurrentState);
                            super.onInterceptTouchEvent(event);
                            return true;
                        }
                        //闭合状态，上滑不拦截

                        break;
                    case 'b':
                        //没有闭合状态，不管是上滑还是下滑都需要拦截, NestedScrollView 本身滑动，内部控件不能滑动
                        if (mCurrentState != AppBarStateChangeListener.State.COLLAPSED && mCurrentState != AppBarStateChangeListener.State.GONE) {
                            super.onInterceptTouchEvent(event);
                            return true;
                        }else {
                            //闭合状态，需要先到顶再拦截和 AppBarLayout 滑动联动，
                            if (innerViewScrollState == null || innerViewScrollState.scrollToTop()) {
                                super.onInterceptTouchEvent(event);
                                return true;
                            }
                        }
                        Log.i("调试滑动", "scrollview 没有拦截 b " + mCurrentState);

                    default:
                        break;
                }
                break;
        }

        super.onInterceptTouchEvent(event);
        return false;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d("AppbarNestedScrollView", "t: " + t);
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

