package com.xiaoming.widgetanimation.textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        showShake();
        //showShake2();
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
}
