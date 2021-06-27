package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xiaoming.a008project.consumption.utils.ScreenUtil;
import com.xiaoming.a008project.fenle.tool.SystemBarUtil;

import in.srain.cube.views.wt.PtrFrameLayout;
import in.srain.cube.views.wt.PtrUIHandler;
import in.srain.cube.views.wt.indicator.PtrIndicator;

public class PtrCommonHeader extends RelativeLayout implements PtrUIHandler {
    private Listener mListener;
    private int mRefreshOffset;
    private int mGoDownOffset;

    private HeaderPtrIv mHeaderPtrIv;
    private int mLoadingFrameY;

    private PtrIndicator mPtrIndicator;

    private float mOneValue;

    private float mScrollThreshold = 400;
    private OnScrollCallback mScrollCallback;


    public PtrCommonHeader(Context context) {
        this(context, null);
    }

    public PtrCommonHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrCommonHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mOneValue = ScreenUtil.getWindowWidth(context) / 375f;
        mRefreshOffset = (int) (mOneValue * 60);
        if (SystemBarUtil.isStatusBarTintSupport()) {
            mRefreshOffset += SystemBarUtil.getStatusBarHeight(context);
        }
        mGoDownOffset = (int) (mOneValue * 180);

        //------------------------------添加loading------------------------------
        int value = (int) (mOneValue * 80);
        mHeaderPtrIv = new HeaderPtrIv(context);
        LayoutParams params = new LayoutParams(value, value);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        mHeaderPtrIv.setLayoutParams(params);
        addView(mHeaderPtrIv);
        setHeaderPtrIv(mHeaderPtrIv);

        reset();
    }

    public void setHeaderPtrIv(HeaderPtrIv headerPtrIv) {
        mHeaderPtrIv = headerPtrIv;
        if (mHeaderPtrIv != null) {
            mHeaderPtrIv.setScaleType(ImageView.ScaleType.FIT_XY);
            mHeaderPtrIv.setMaxAlpha(0.9f);
//            mHeaderPtrIv.setColorFilter(0xFFD0D2D3, PorterDuff.Mode.SRC_IN);
            // 总共31帧 第22帧是刚好在刷新位置
            mLoadingFrameY = (int) (mRefreshOffset * (31f / 22f)) + 1;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        changeLoadingLayout(mPtrIndicator);
    }

    private void changeLoadingLayout(PtrIndicator ptrIndicator) {
        if (ptrIndicator != null && mHeaderPtrIv != null) {
            int y = ptrIndicator.getCurrentPosY();
            float percent = y * 1f / mRefreshOffset;
            if (percent > 1f) {
                percent = 1f;
            }
            int bottom = y;
            int top = bottom - mHeaderPtrIv.getMeasuredHeight();
            int left = mHeaderPtrIv.getLeft();
            int right = left + mHeaderPtrIv.getMeasuredWidth();
            // 这个loading图片宽度240 有68透明边距
            float paddingDelta = mHeaderPtrIv.getMeasuredHeight() * 68f / 240f;
            // 这个偏差处理 主要是图片本身有太大的透明边距 不想他占距离
            int offset = (int) (paddingDelta * percent);
            mHeaderPtrIv.layout(left, top /*+ offset*/, right, bottom /*+ offset*/);
        }
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        reset();
        if (mListener != null) {
            mListener.onUIReset();
        }
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        if (mHeaderPtrIv != null) {
            mHeaderPtrIv.startLoading();
        }

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        if (mHeaderPtrIv != null) {
            mHeaderPtrIv.refreshComplete();
        }
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        mPtrIndicator = ptrIndicator;
        if (getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
        changeLoadingLayout(ptrIndicator);
        int y = ptrIndicator.getCurrentPosY();
        if (mListener != null) {
            mListener.onUIPositionChange(y);
        }
        changeLoadingState(frame, ptrIndicator, y);


        /*if (NewMembersData.getInstance().isNewMember() && mPtrIndicator.getCurrentPosY() > mRefreshOffset * 1.8f) {
            if (mScrollCallback != null) {
                mScrollCallback.onScrollCallback();
            }
        }*/
    }

    public void setScrollThreshold(float scrollThreshold) {
        mScrollThreshold = scrollThreshold;
    }

    public void setScrollCallback(OnScrollCallback scrollCallback) {
        mScrollCallback = scrollCallback;
    }

    public interface OnScrollCallback{
        void onScrollCallback();
    }

    private void changeLoadingState(PtrFrameLayout frame, PtrIndicator ptrIndicator, int y) {
        if (mHeaderPtrIv != null) {
            changeLoadingLayout(mPtrIndicator);
            float percent = y * 1f / mLoadingFrameY;
            mHeaderPtrIv.onUIPositionChange(frame.getStatus(), percent, y);
        }
    }

    @Override
    public void onUIRefreshGoDown(final PtrFrameLayout frame, boolean begin, boolean finish) {
        if (finish) {
            Context context = frame.getContext();
            frame.postDelayed(new Runnable() {
                @Override
                public void run() {
                    frame.refreshComplete();
                }
            }, 100);
        }
    }

    public int getRefreshOffset() {
        return mRefreshOffset;
    }

    public int getGoDownOffset() {
        return mGoDownOffset;
    }

    public long getDelayLoadingComplete(long delta) {
        if (mHeaderPtrIv != null && mHeaderPtrIv.getVisibility() == VISIBLE) {
            return mHeaderPtrIv.getDelayLoadingComplete(delta);
        } else {
            return delta;
        }
    }

    public void release() {

    }

    public void reset() {
        if (mHeaderPtrIv != null) {
            mHeaderPtrIv.reset();
        }
        setVisibility(GONE);
    }

    public void bindData() {
        if (mHeaderPtrIv != null) {
            mHeaderPtrIv.setVisibility(VISIBLE);
        }
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public interface Listener {
        void onUIPositionChange(int y);

        void onUIReset();
    }

}
