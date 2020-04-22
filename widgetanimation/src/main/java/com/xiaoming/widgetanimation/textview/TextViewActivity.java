package com.xiaoming.widgetanimation.textview;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.xiaoming.widgetanimation.R;

public class TextViewActivity extends AppCompatActivity {
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        tvShow = findViewById(R.id.tv_show);
        //showBlowUpAnimation();
        //showShake();
        //showShake2();
        //showShake3();
        //showShake4();

        showBlowUpAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showShake3();
            }
        }, 1000);
    }

    //从小变大
    private void showBlowUpAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.textview_blow_up);
        animation.setFillAfter(true); //结束后保存状态
        tvShow.startAnimation(animation);
    }

    //左右/上下抖动
    private void showShake() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.textview_shake);
        tvShow.startAnimation(animation);
    }

    //Java代码实现左右摇摆/上下摇摆动画
    private void showShake2() {
        /*TranslateAnimation animation = new TranslateAnimation(0, 0, -20, 20);
        animation.setInterpolator(new OvershootInterpolator());
        animation.setDuration(500);
        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.REVERSE);
        tvShow.startAnimation(animation);*/

        //左右不停摇摆
        TranslateAnimation alphaAnimation2 = new TranslateAnimation(150f, 350f, 50, 50);
        alphaAnimation2.setDuration(1000); //动画持续时间
        alphaAnimation2.setRepeatCount(Animation.INFINITE);
        alphaAnimation2.setRepeatMode(Animation.REVERSE);
        tvShow.setAnimation(alphaAnimation2); //设置给控件
        alphaAnimation2.start();
    }

    private void showRotate() {

    }

    //https://blog.csdn.net/zhangcanyan/article/details/54896456?utm_source=blogxgwz4
    //https://github.com/lzyzsd/AndroidSeekAttention
   private void showShake3() {
       ObjectAnimator animator = tada(tvShow);
       //animator.setRepeatCount(ValueAnimator.INFINITE);
       animator.setRepeatCount(0);
       animator.start();
   }

    private void showShake4() {
        ObjectAnimator nopeAnimator = nope(tvShow);
        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
        nopeAnimator.start();
    }

    public static ObjectAnimator tada(View view) {
        return tada(view, 1f);
    }

    public static ObjectAnimator tada(View view, float shakeFactor) {

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -3f * shakeFactor),
                Keyframe.ofFloat(.2f, -3f * shakeFactor),
                Keyframe.ofFloat(.3f, 3f * shakeFactor),
                Keyframe.ofFloat(.4f, -3f * shakeFactor),
                Keyframe.ofFloat(.5f, 3f * shakeFactor),
                Keyframe.ofFloat(.6f, -3f * shakeFactor),
                Keyframe.ofFloat(.7f, 3f * shakeFactor),
                Keyframe.ofFloat(.8f, -3f * shakeFactor),
                Keyframe.ofFloat(.9f, 3f * shakeFactor),
                Keyframe.ofFloat(1f, 0)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate).
                setDuration(1000);
    }

    public static ObjectAnimator nope(View view) {
        int delta = view.getResources().getDimensionPixelOffset(R.dimen.spacing_medium);

        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, -delta),
                Keyframe.ofFloat(.26f, delta),
                Keyframe.ofFloat(.42f, -delta),
                Keyframe.ofFloat(.58f, delta),
                Keyframe.ofFloat(.74f, -delta),
                Keyframe.ofFloat(.90f, delta),
                Keyframe.ofFloat(1f, 0f)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX).
                setDuration(500);
    }
}
