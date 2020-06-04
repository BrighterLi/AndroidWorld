package com.xiaoming.widgetanimation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.xiaoming.widgetanimation.lottie.LottieActivity;
import com.xiaoming.widgetanimation.textview.TextViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTextViewAnim;
    private Button btnLottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnTextViewAnim = findViewById(R.id.bt_textview_anim);
        btnLottie = findViewById(R.id.bt_lottie);
        btnTextViewAnim.setOnClickListener(this);
        btnLottie.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_textview_anim:
                startActivity(new Intent(MainActivity.this, TextViewActivity.class));
                break;
            case R.id.bt_lottie:
                startActivity(new Intent(MainActivity.this, LottieActivity.class));
                break;
        }
    }
}
