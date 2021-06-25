package com.widget.animation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.widget.R;

import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {
    private Button mBtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        overridePendingTransition(R.anim.popwindow_no_anim, R.anim.popwindow_out);

        mBtBack = findViewById(R.id.bt_finish);
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
