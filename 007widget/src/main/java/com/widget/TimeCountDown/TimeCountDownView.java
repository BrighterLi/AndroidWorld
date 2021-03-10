package com.widget.TimeCountDown;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.widget.R;

import java.text.DecimalFormat;


/**
 * Created by brightli on 2021/3/10
 */
public class TimeCountDownView extends RelativeLayout {

    private final TextView mCountDownHour;
    private final TextView mCountDownMin;
    private final TextView mCountDownSecond;
    private TimeCountDownTask downTask;
    private PrivilegeCountDownListener mListener;
    private DecimalFormat mDecimalFormat;
    private long mSwitchTime = 1000;
    private long mCurrentTime = 0;
    private long mSystemStopTime = 0;
    private long mSystemStartTime = 0;

    public TimeCountDownView(Context context) {
        this(context, null);
    }

    public TimeCountDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeCountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_time_count_down, this);
        mCountDownHour = (TextView) findViewById(R.id.mCountDownHour);
        mCountDownMin = (TextView) findViewById(R.id.mCountDownMin);
        mCountDownSecond = (TextView) findViewById(R.id.mCountDownSecond);
        downTask = new TimeCountDownTask();
    }


    public String initCount(long time) {
        long dayNum = time / (24 * 60 * 60);
        long hourNum = (time - dayNum * 24 * 60 * 60) / (60 * 60);
        long minNum = (time - dayNum * 24 * 60 * 60 - hourNum * 60 * 60) / 60;
        long secondNum = time - dayNum * 24 * 60 * 60 - hourNum * 60 * 60 - minNum * 60;
        return formatTime(hourNum) + ":" + formatTime(minNum) + ":" + formatTime(secondNum);
    }

    private String formatTime(long num) {
        if (mDecimalFormat == null) {
            mDecimalFormat = new DecimalFormat("00");
        }
        return mDecimalFormat.format(num);
    }

    /**
     * @param time 传入时间为秒单位
     */
    public void setTime(long time) {
        if (downTask != null) {
            downTask.stop();
        }
        if (time <= 0) {
            downTask = null;
            mCountDownHour.setText("00");
            mCountDownMin.setText("00");
            mCountDownSecond.setText("00");
            return;
        }
        if (downTask == null) {
            downTask = new TimeCountDownTask();
        }
        downTask.start(time);

    }

    public void setPrivilegeCountDownListener(PrivilegeCountDownListener listener) {
        mListener = listener;
    }

    public interface PrivilegeCountDownListener {
        void doForCountDownOver();
    }

    class TimeCountDownTask extends Handler implements Runnable {

        private boolean isStart = true;
        private long mTime = 0;

        @Override
        public void run() {
            mCurrentTime = mTime;
            if (mTime > 0) {
                String countTime = initCount(mTime);
                String[] split = countTime.split(":");
                mCountDownHour.setText(split[0]);
                mCountDownMin.setText(split[1]);
                mCountDownSecond.setText(split[2]);
                --mTime;
                postDelayed(this, mSwitchTime);
            } else {
                stop();
            }
        }

        public void start(long time) {
            this.mTime = time;
            if (isStart) {
                stop();
                post(this);
                isStart = false;
            }
        }

        public void stop() {
            isStart = true;
            mCurrentTime = mTime;
            mSystemStopTime = System.currentTimeMillis() / 1000;
            removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (downTask != null) {
            downTask.stop();
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getVisibility() == View.VISIBLE) {
            if (downTask != null) {
                mSystemStartTime = System.currentTimeMillis() / 1000;
                downTask.start(mCurrentTime - (mSystemStartTime - mSystemStopTime));
            }
        }
    }


    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        switch (visibility) {
            case VISIBLE:
                if (getVisibility() == View.VISIBLE) {
                    if (downTask != null) {
                        mSystemStartTime = System.currentTimeMillis() / 1000;
                        downTask.start(mCurrentTime - (mSystemStartTime - mSystemStopTime));
                    }
                }
                break;
            case GONE:
                if (downTask != null) {
                    downTask.stop();
                }
                break;
            case INVISIBLE:
                if (downTask != null) {
                    downTask.stop();
                }
                break;
        }
    }
}

