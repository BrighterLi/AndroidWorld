package com.widget.animation.lottie;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.widget.R;


public class LottieActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtLottieAnim1;
    private Button mBtLottieAnim2;
    private Button mBtLottieAnim3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);

        initView();
    }

    private void initView() {
        mBtLottieAnim1 = findViewById(R.id.bt_lottie_anim);
        mBtLottieAnim2 = findViewById(R.id.bt_lottie_anim2);
        mBtLottieAnim3 = findViewById(R.id.bt_lottie_anim3);

        mBtLottieAnim1.setOnClickListener(this);
        mBtLottieAnim2.setOnClickListener(this);
        mBtLottieAnim3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_lottie_anim:
                startActivity(new Intent(LottieActivity.this, LottieAnimationActivity.class));
                break;
            case R.id.bt_lottie_anim2:
                startActivity(new Intent(LottieActivity.this, LottieAnimationActivity2.class));
                break;
            case R.id.bt_lottie_anim3:
                startActivity(new Intent(LottieActivity.this, LottieNetworkAnimActivity.class));
                break;
        }
    }
}
