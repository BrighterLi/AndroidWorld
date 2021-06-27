package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.header;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoming.a008project.R;

import androidx.annotation.NonNull;

public class CollapsibleContent implements View.OnClickListener {
    private ViewStub viewStub;
    private FloatInfo mFloatInfo;

    private View root;
    private CircleProgress circleProgressView;
    private TextView textRaise;
    private TextView textAvailableQuota;
    private TextView textUnit;
    private TextView textAvailableQuotaTip;
    private ImageView imgTmpQuota;
    private FrameLayout frameContainer;


    private String mCurrentFloatType;
    private ValueAnimator mShineAnimator;
    //第一次展开展示动画
    private boolean mHasShowAnim = false;


    public CollapsibleContent(@NonNull ViewStub viewStub) {
        this.viewStub = viewStub;
    }

    public void show() {
        if (viewStub != null) {
            root = viewStub.inflate();
            circleProgressView = root.findViewById(R.id.circleProgressView);
            textRaise = root.findViewById(R.id.textRaise);
            textAvailableQuota = root.findViewById(R.id.textAvailableQuota);
            textUnit = root.findViewById(R.id.textUnit);
            textAvailableQuotaTip = root.findViewById(R.id.textAvailableQuotaTip);
            imgTmpQuota = root.findViewById(R.id.imgTmpQuota);
            frameContainer = root.findViewById(R.id.frameContainer);
            viewStub = null;
        }
        root.setVisibility(View.VISIBLE);
    }





    public void setVisibleGone() {
        if (root != null) {
            root.setVisibility(View.GONE);
            cancelShine();
        }
    }

    private void setShineAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(255, 0, 255);
        animator.setDuration(1600);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int alpha = (int) animation.getAnimatedValue();
                imgTmpQuota.setImageAlpha(alpha);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            private boolean isCancel;

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isCancel) {
                    imgTmpQuota.setImageResource(R.drawable.emoji_default);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isCancel = true;
            }
        });
        mShineAnimator = animator;
    }


    private void cancelShine() {
        if (mShineAnimator != null) {
            if (mShineAnimator.isRunning()) {
                mShineAnimator.cancel();
            }
            mShineAnimator = null;
        }
    }

    @Override
    public void onClick(View v) {
    }
}

