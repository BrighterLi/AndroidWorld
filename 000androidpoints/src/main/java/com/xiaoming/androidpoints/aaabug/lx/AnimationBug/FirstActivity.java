package com.xiaoming.androidpoints.aaabug.lx.AnimationBug;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;

public class FirstActivity extends AppCompatActivity {
    private Button mBtStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mBtStart = findViewById(R.id.bt_start_activity);
        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThirdActivity();
                mBtStart.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startSecondActivity();
                    }
                },1000);
            }
        });
    }

    private void startSecondActivity() {
        startActivity(new Intent(FirstActivity.this, SecondActivity.class));
        overridePendingTransition(R.anim.popwindow_in2, 0);
    }

    private void startThirdActivity() {
        startActivity(new Intent(FirstActivity.this, ThirdActivity.class));
        overridePendingTransition(0, 0);
    }
}
