package com.widget.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.widget.R;

public class AnimationTestActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_test);
        btl = findViewById(R.id.bt1);
        btl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                startActivity(new Intent(AnimationTestActivity.this, Animation1Activity.class));
                break;
        }
    }
}
