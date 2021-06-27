package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.xiaoming.a008project.R;
import com.xiaoming.a008project.fenle.view.GifFrame;

import androidx.annotation.RequiresApi;
import in.srain.cube.views.wt.PtrFrameLayout;

@SuppressLint("AppCompatCustomView")
public class HeaderPtrIv extends ImageView {

    private static final String TAG = "GifIvLoading";

    private boolean isLoading = false;
    private int mPullFrameNum = 0;
    private int mPullFrameIndex = -1;
    private int mLoadingFrameNum = 0;
    private float mMaxAlpha = 1f;
    private byte mStatus = PtrFrameLayout.PTR_STATUS_INIT;
    private GifFrame mPullGifFrame;
    private GifFrame mLoadingGifFrame;
    private GifFrame.Listener mListener = new GifFrame.Listener() {
        @Override
        public void onChange(Bitmap bitmap) {
            if (isLoading) {
                setImageBitmap(bitmap);
            }
        }

        @Override
        public void onOneLoopEnd() {
            // 确保刷新完收上去的时候保持在最后一帧图像
            if (mStatus == PtrFrameLayout.PTR_STATUS_COMPLETE) {
                mLoadingGifFrame.stopPlay();
            }
        }
    };


    public HeaderPtrIv(Context context) {
        super(context);
        init(context);
    }

    public HeaderPtrIv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderPtrIv(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public HeaderPtrIv(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        preparePull(context);
        prepareLoading(context);
    }

    public void setMaxAlpha(float maxAlpha) {
        if (maxAlpha >= 0 && maxAlpha <= 1) {
            mMaxAlpha = maxAlpha;
        }
    }

    private void preparePull(Context context) {
        mPullGifFrame = new GifFrame(context);

        mPullGifFrame.addRes(0, R.drawable.default_head);
        mPullGifFrame.addRes(1, R.drawable.emoji_default);

        mPullFrameNum = mPullGifFrame.frameNum();
    }

    private void prepareLoading(Context context) {
        mLoadingGifFrame = new GifFrame(context);

        mLoadingGifFrame.addRes(0, R.drawable.default_head);
        mLoadingGifFrame.addRes(1, R.drawable.emoji_default);
        mLoadingGifFrame.setFrameDuration(1);
        mLoadingGifFrame.setListener(mListener);

        mLoadingFrameNum = mLoadingGifFrame.frameNum();
    }


    public void onUIPositionChange(byte status, float percent, int y) {
        mStatus = status;
        if (percent <= 0) {
            reset();
            return;
        }
        if (getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
        if (percent <= 1) {
            // 变更图片
            if (!isLoading) {
                syncPullImage(percent);
            } else if (status == PtrFrameLayout.PTR_STATUS_COMPLETE) {
                // 确保刷新完收上去的时候保持在最后一帧图像
                stopLoadingImage();
            }
            // 变更透明度
            syncAlpha(percent);
        }
    }

    private void syncPullImage(float percent) {
        if (mPullFrameNum > 0) {
            int index = Math.round(percent * mPullFrameNum);
            if (index != mPullFrameIndex && index < mPullFrameNum) {
                Bitmap bitmap = mPullGifFrame.getBitmap(index, true);
                if (bitmap != null) {
                    setImageBitmap(bitmap);
                    mPullFrameIndex = index;
                }
            }
        }
    }


    private void stopLoadingImage() {
        if (mLoadingGifFrame.isRunning()) {
            int index = mLoadingGifFrame.getCurrentFrameIndex();
            if (index == mLoadingFrameNum - 1 || index < mLoadingFrameNum / 4) {
                mLoadingGifFrame.stopPlay();
            }
        }
    }

    public long getDelayLoadingComplete(long delta) {
        long delay = 0;
        if (mLoadingFrameNum > 1 && mLoadingGifFrame.isRunning()) {
            try {
                int index = mLoadingGifFrame.getCurrentFrameIndex();
                delay = (mLoadingFrameNum - index - 1) * mLoadingGifFrame.getFrameDuration();
                long offset = delay - delta;
                delay = offset >= mLoadingGifFrame.getFrameDuration()
                        ? offset : offset + mLoadingGifFrame.getOneLoopDuration();
            } catch (Throwable e) {
                Log.e(TAG, "delay", e);
            }
        } else {
            delay = 100;
        }
        return delay > 0 ? delay : 1000;
    }


    private void syncAlpha(float percent) {
        float v = Math.min(percent * 2, 1f);
        if (v > mMaxAlpha) {
            v = mMaxAlpha;
        }
        if (getAlpha() != v) {
            setAlpha(v);
        }
    }


    public void startLoading() {
        if (getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
        mLoadingGifFrame.stopPlay();
        mLoadingGifFrame.startPlay();
        isLoading = true;
    }


    public void refreshComplete() {
        mStatus = PtrFrameLayout.PTR_STATUS_COMPLETE;
    }


    public void reset() {
        if (getAlpha() != 1f) {
            setAlpha(1f);
        }
        setImageDrawable(null);
        mLoadingGifFrame.stopPlay();
        isLoading = false;
    }


}

