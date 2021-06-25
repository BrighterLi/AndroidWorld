package com.widget.animation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.widget.R;

import android.view.View;
import android.widget.Button;

public class Activity1 extends AppCompatActivity {
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        //overridePendingTransition(R.anim.popwindow_no_anim, R.anim.popwindow_out_from_bottom_to_top);
        //overridePendingTransition(R.anim.popwindow_in, R.anim.popwindow_out_from_bottom_to_top);
        overridePendingTransition(0, 0);
        mBt = findViewById(R.id.bt_jump);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.popwindow_in, R.anim.popwindow_no_anim);
                startActivity(new Intent(Activity1.this, Activity2.class));
            }
        });
    }
}
