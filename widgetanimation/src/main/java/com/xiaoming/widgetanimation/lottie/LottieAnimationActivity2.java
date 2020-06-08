package com.xiaoming.widgetanimation.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.xiaoming.widgetanimation.R;

public class LottieAnimationActivity2 extends AppCompatActivity implements View.OnClickListener{
    private Button mBtStart, mBtStop;
    private TextView mTvSeek;
    private LottieAnimationView mLottieAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_anim2);

        initView();
    }

    private void initView() {
        mBtStart = findViewById(R.id.bt_start);
        mBtStop = findViewById(R.id.bt_stop);
        mTvSeek = findViewById(R.id.tv_seek);
        mLottieAnimView = findViewById(R.id.lottie_view2);

        mBtStart.setOnClickListener(this);
        mBtStop.setOnClickListener(this);

        //初始化，引入json
        LottieComposition.Factory.fromAssetFileName(this, "loading.json", new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(@Nullable LottieComposition lottieComposition) {
                //这句话是关键，相当于将空间和json绑定
                mLottieAnimView.setComposition(lottieComposition);
                //mLottieAnimView.playAnimation();
            }
        });

        //添加动画更新（进度）的监听，这里的监听可以理解为播放进度
        mLottieAnimView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mTvSeek.setText("动画进度" + animation.getAnimatedFraction() * 100 + "%");
            }
        });

        mLottieAnimView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                //动画开始播放的时候会调用
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //动画结束的时候回调用
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                //动画主动取消的时候会调用
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                //loop的时候会调用，注意是重新播放的时候会调用
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                startAnim();
                break;
            case R.id.bt_stop:
                stopAnim();
                break;
        }
    }

    //开始动画
    private void startAnim() {
        boolean isPlaying = mLottieAnimView.isAnimating();
        if(!isPlaying) {
            mLottieAnimView.setProgress(0f);
            mLottieAnimView.playAnimation();
        }
    }

    //停止动画
    private void stopAnim() {
        boolean isPlaying = mLottieAnimView.isAnimating();
        if(isPlaying) {
            mLottieAnimView.cancelAnimation();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLottieAnimView.cancelAnimation();
        //移除监听
    }
}
