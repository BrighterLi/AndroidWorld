package com.widget.animation;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.widget.R;
import com.widget.animation.activity.Activity1;
import com.widget.animation.textview.TextViewActivity;

public class AnimationTestActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btl;
    private Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_test);
        btl = findViewById(R.id.bt1);
        btl.setOnClickListener(this);
        bt2 = findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                //startActivity(new Intent(AnimationTestActivity.this, Animation1Activity.class));
                startActivity(new Intent(AnimationTestActivity.this, TextViewActivity.class));
                break;
            case R.id.bt2:
                startActivity(new Intent(AnimationTestActivity.this, Activity1.class));
                break;
        }
    }
}
