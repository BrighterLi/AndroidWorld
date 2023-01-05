package com.widget.animation;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.widget.R;
import com.widget.animation.activity.Activity1;
import com.widget.animation.moveview.MoveViewActivity;
import com.widget.animation.pagescroll.PageScrollActivity;
import com.widget.animation.propertyanimation.ObjectAnimatorActivity;
import com.widget.animation.textview.TextViewActivity;

public class AnimationTestActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btl;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_test);
        btl = findViewById(R.id.bt1);
        btl.setOnClickListener(this);
        bt2 = findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
        bt3 = findViewById(R.id.bt3);
        bt3.setOnClickListener(this);
        bt4 = findViewById(R.id.bt4);
        bt4.setOnClickListener(this);
        bt5 = findViewById(R.id.bt5);
        bt5.setOnClickListener(this);
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
            case R.id.bt3:
                startActivity(new Intent(AnimationTestActivity.this, ObjectAnimatorActivity.class));
                break;
            case R.id.bt4:
                startActivity(new Intent(AnimationTestActivity.this, MoveViewActivity.class));
                break;
            case R.id.bt5:
                //startActivity(new Intent(AnimationTestActivity.this, PageScrollActivity.class));
                startActivity(new Intent(AnimationTestActivity.this, PageScrollActivity.class));
                break;
        }
    }
}
