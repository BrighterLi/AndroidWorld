package com.widget.banner.banner2;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.widget.BuildConfig;

import java.lang.reflect.Field;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Banner extends CustomViewPager {

    public static final int VIEWPAGER_TOTAL_NUMBER = Integer.MAX_VALUE;
    private int mSwitchTime = 5000;
    private int mDurationTime = 400;
    private Interpolator mInterpolator = new DecelerateInterpolator();
    private AutoSwitchTask mAutoSwitchTask;
    private BannerScrolledListener mBannerScrolledListener;
    private BannerAdapter mAdapter;
    private BannerPointInterface mBannerPoint;
    private boolean isStop = false;
    private boolean isFromAttachRefresh = false;
    //记录手指最后抬起的时间，用于判断是自动滑动还是手动滑动
    private long mLastOnTouchUpTime = -1;
    private Field mScrollerField;
    public int repeatSwitchTimes = -1;
    private int mAlreadySwitchTimes = 0;

    public int getRepeatSwitchTimes() {
        return repeatSwitchTimes;
    }

    public void setRepeatSwitchTimes(int repeatSwitchTimes) {
        this.repeatSwitchTimes = repeatSwitchTimes;
    }

    Scroller mDefaultScroller = new Scroller(getContext(),
            new Interpolator() {
                @Override
                public float getInterpolation(float t) {
                    t -= 1.0f;
                    return t * t * t * t * t + 1.0f;
                }
            });

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnPageChangeListener(onPageChangeListener);
        setOverScrollMode(OVER_SCROLL_NEVER);
        setOnViewPagerTouchEventListener(onViewPagerTouchEvent);
        setDurationTimeAndInterpolator(mDurationTime, null);
    }

    public int getDurationTime() {
        return mDurationTime;
    }

    public Interpolator getInterpolator() {
        return mInterpolator;
    }

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mBannerScrolledListener != null) {
                List list = mAdapter.getData();
                if (list != null && list.size() > 0) {
                    mBannerScrolledListener.onPageScrolledOffset(positionOffset, position % list.size(), list.size());
                } else {
                    mBannerScrolledListener.onPageScrolledOffset(positionOffset, 0, 0);
                }
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (mAdapter == null) {
                return;
            }

            //记录手指离开屏幕的时间，如果下次onPageSelected的时间差值小于预设差值，则为手动滑动，否则为自动滑动
            boolean isAutoScroll;
            if (mLastOnTouchUpTime != -1 && System.currentTimeMillis() - mLastOnTouchUpTime < mSwitchTime) {//是手动滑动
                isAutoScroll = false;
            } else {//是自动滚动
                isAutoScroll = true;
            }
            mLastOnTouchUpTime = -1;//清空记录的时间

            List list = mAdapter.getData();
            if (list != null && list.size() > 0) {
                position = position % list.size();
                if (mBannerScrolledListener != null && !isFromAttachRefresh) {
                    mBannerScrolledListener.onPageSelected(position, isAutoScroll);
                }
                if (mBannerPoint != null) {
                    mBannerPoint.onSelected(position);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private OnViewPagerTouchEvent onViewPagerTouchEvent = new CustomViewPager.OnViewPagerTouchEvent() {
        @Override
        public void onTouchDown() {
            log("onTouchDown");
            stopCustomBannerSwitch();
        }

        @Override
        public void onTouchLeave() {
            mLastOnTouchUpTime = System.currentTimeMillis();
            log("-------------------------->>>onTouchUp 记录离开时间：" + mLastOnTouchUpTime);
            if (mAdapter != null
                    && mAdapter.getData() != null
                    && mAdapter.getData().size() > 1) {
                startCustomBannerSwitch();
            }
        }
    };

    public void setBannerPoint(BannerPointInterface bannerPoint) {
        mBannerPoint = bannerPoint;
    }

    public void setAdapter(BannerAdapter adapter) {
        setAdapter(adapter, true, true);
    }

    /**
     * @param adapter
     * @param isNeedStartFormCenter 是否需要从中间开启轮播
     * @param isNeedAutoScrollNow   是否立即开启自动滚动 （用于部分不想立即开启启动的场景）
     */
    public void setAdapter(BannerAdapter adapter, boolean isNeedStartFormCenter, boolean isNeedAutoScrollNow) {
        if (adapter == null) {
            return;
        }
        super.setAdapter(adapter);
        mAdapter = adapter;
        notifyChanged(isNeedStartFormCenter, isNeedAutoScrollNow);
    }

    private void notifyChanged(boolean isNeedStartFormCenter, boolean isNeedAutoScrollNow) {
        if (mAdapter == null) {
            return;
        }
        initBannerPoint();
        List list = mAdapter.getData();
        int size = list == null ? 0 : list.size();
        if (size > 1) {
            setScrollUnable(false);
            setBannerSwitchable(true);
            initViewPagerSwitch(list, isNeedStartFormCenter, isNeedAutoScrollNow);
        } else {
            setScrollUnable(true);
            setBannerSwitchable(false);
            stopCustomBannerSwitch();
            mAutoSwitchTask = null;
        }
    }

    public void setBannerScrolledListener(BannerScrolledListener listener) {
        mBannerScrolledListener = listener;
    }


    /**
     * 设置动画延时时间 和插值器
     *
     * @param durationTime
     * @param interpolator
     */
    public void setDurationTimeAndInterpolator(int durationTime, Interpolator interpolator) {
        if (durationTime > 0) {
            mDurationTime = durationTime;
        }
        if (interpolator != null) {
            mInterpolator = interpolator;
        }
        try {
            Field field = getField();
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(), mInterpolator);
            scroller.setFixedDuration(mDurationTime);
            field.set(this, scroller);
        } catch (Exception e) {
        }
    }

    @NonNull
    private Field getField() throws NoSuchFieldException {
        if (mScrollerField == null) {
            mScrollerField = ViewPager.class.getDeclaredField("mScroller");
            mScrollerField.setAccessible(true);
        }
        return mScrollerField;
    }


    /**
     * 设置自定义动画效果
     */
    private void setCustomAnim() {
        log("设置自定义动画效果");
        setDurationTimeAndInterpolator(mDurationTime, mInterpolator);
    }

    /**
     * 恢复默认动画效果
     */
    private void restoreDefaultAnim() {
        log("恢复默认动画效果");
        try {
            Field field = getField();
            field.set(this, mDefaultScroller);
        } catch (Exception e) {
        }
    }

    public void setBannerSwitchTime(int time) {
        mSwitchTime = time;
    }

    public int getBannerSwitchTime() {
        return mSwitchTime;
    }

    private void initViewPagerSwitch(List list, boolean isNeedStartFormCenter, boolean isNeedAutoScrollNow) {

        if (list != null && list.size() > 1) {
            if (isNeedStartFormCenter) {
                int middle = mAdapter.getCount() / 2;
                int extra = middle % list.size();
                int destItem = middle - extra;
                if (destItem < 0) {
                    destItem = 0;
                }
                setCurrentItem(destItem, false);
            }
            if (mAutoSwitchTask == null) {
                mAutoSwitchTask = new AutoSwitchTask(this);
            }
            if (isNeedAutoScrollNow) {
                mAutoSwitchTask.start();
            }
        }
    }

    public void startCustomBannerSwitch() {
        startCustomBannerSwitch(false);
    }

    public void startCustomBannerSwitch(boolean refresh) {
        //Modify by denny pager的个数大于1时，才转换
        if (mAdapter == null) {
            return;
        }
        if (mAdapter.getData() == null || mAdapter.getData().size() <= 1) {
            return;
        }
        if (mAutoSwitchTask == null) {
            mAutoSwitchTask = new AutoSwitchTask(this);
        }
        mAutoSwitchTask.start();
        if (refresh) {
            isFromAttachRefresh = true;
            setCurrentItem(getCurrentItem() + 1, false);
            setCurrentItem(getCurrentItem() - 1, false);
            isFromAttachRefresh = false;
        }
    }

    public void stopCustomBannerSwitch() {
        if (mAutoSwitchTask != null) {
            mAutoSwitchTask.stop();
        }
        if (!isStop) {
            restoreDefaultAnim();
            isStop = true;
        }
    }

    public boolean setBannerSwitchable(boolean switchable) {
        if (mAutoSwitchTask != null && mAutoSwitchTask.getSwitchable() != switchable) {
            mAutoSwitchTask.setSwitchable(switchable);
            if (BuildConfig.DEBUG) {
                Log.i("Banner", "setSwitchable=" + switchable);
            }
            return true;
        }
        return false;
    }

    public boolean getBannerSwitchable() {
        return mAutoSwitchTask != null && mAutoSwitchTask.getSwitchable();
    }

    private void initBannerPoint() {
        int size = 0;
        List list = mAdapter.getData();
        if (list != null && list.size() > 1) {
            size = list.size();
        }

        if (mBannerPoint != null) {
            mBannerPoint.initPoint(size);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        log("onDetachedFromWindow");
        stopCustomBannerSwitch();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        log("onAttachedToWindow");
        //解决再次attach切换无动画问题
        startCustomBannerSwitch(true);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        switchOnVisibilityChanged(visibility);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        switchOnVisibilityChanged(visibility);
    }

    private void switchOnVisibilityChanged(int visibility) {
        switch (visibility) {
            case VISIBLE:
                if (getVisibility() == View.VISIBLE) {
                    startCustomBannerSwitch();
                }
                break;
            case INVISIBLE:
            case GONE:
                stopCustomBannerSwitch();
                break;
            default:
                break;
        }
    }

    public interface BannerScrolledListener {
        void onPageScrolledOffset(float positionOffset, int position, int size);

        void onPageSelected(int position, boolean isAutoScroll);
    }

    class AutoSwitchTask extends Handler {
        private ViewPager viewPager;
        private boolean canSwitch = true;
        private boolean canStart = true;

        AutoSwitchTask(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        public void start() {
            if (canStart) {
                stop();
                postDelayed(runnable, mSwitchTime);
                canStart = false;
            }
        }

        public void stop() {
            canStart = true;
            removeCallbacks(runnable);
        }

        private void doSwitch(boolean isContinue) {
            if (canSwitch) {
                int position = viewPager.getCurrentItem();
                PagerAdapter adapter = viewPager.getAdapter();
                if (adapter != null) {
                    if (position != adapter.getCount() - 1) {
                        viewPager.setCurrentItem(++position);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                    if (isContinue) {
                        postDelayed(runnable, mSwitchTime);
                    }
                }
            }
        }

        public void setSwitchable(boolean switchable) {
            canSwitch = switchable;
            removeCallbacks(runnable);
            if (switchable) {
                postDelayed(runnable, mSwitchTime);
            }
        }

        public boolean getSwitchable() {
            return canSwitch;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (isStop) {
                    setCustomAnim();
                    isStop = false;
                }

                //doSwitch(true);
                if(repeatSwitchTimes == -1) {
                    Log.i("Banner", "bright8#repeatSwitchTimes11111");
                    doSwitch(true);
                } else if(mAlreadySwitchTimes < repeatSwitchTimes) {
                    Log.i("Banner", "bright8#repeatSwitchTimes222222");
                    mAlreadySwitchTimes++;
                    doSwitch(true);
                } else {
                    Log.i("Banner", "bright8#repeatSwitchTimes33333");
                    removeCallbacks(runnable);
                }
            }
        };

    }

    private void log(String tips) {
        if (BuildConfig.DEBUG) {
            Log.e("Banner", tips);
        }
    }

    public int getCurrentIndex() {
        int size = 1;
        if (mAdapter != null
                && mAdapter.getData() != null
                && mAdapter.getData().size() > 1) {
            size = mAdapter.getData().size();
        }
        return getCurrentItem() % size;
    }
}

