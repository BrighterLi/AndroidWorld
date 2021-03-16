package com.widget.animation;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.widget.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Animation1Activity extends AppCompatActivity {
    private Timer myTimer;
    private ImageView imageview;
    private ScaleAnimation animation;
    private AlphaAnimation animation2;
    private AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer1);

        imageview = findViewById(R.id.imageView);
        creatAnimation();
        creatAnimation2();
        createAnimationSet();
        showAnimation(imageview);

    }

    private void showAnimation(final View view) {
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //view.startAnimation(animation);
                        //view.startAnimation(animation2);
                        view.startAnimation(animationSet);
                        showAnimation(view);
                    }
                });
            }
        }, 6000);
    }

    //缩放动画
    private void creatAnimation() {
        animation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,1, 0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
    }

    //透明度动画
    private void creatAnimation2() {
        animation2 = new AlphaAnimation(0.0f, 1.0f);
        animation2.setDuration(500);
        animation2.setFillAfter(true);
    }

    //动画组合
    private void createAnimationSet() {
        animationSet = new AnimationSet(true);
        //ScaleAnimation animation = new ScaleAnimation(0,1.0f,0,1.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                //ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,1, 0.5f);
        AlphaAnimation animation2 = new AlphaAnimation(0.0f, 1.0f);
        //animationSet.addAnimation(animation);
        animation2.setInterpolator(new OvershootInterpolator()); //插值器
        animationSet.addAnimation(animation2);
        animationSet.setDuration(500);
    }

}
