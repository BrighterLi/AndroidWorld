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
        //overridePendingTransition(R.anim.popwindow_no_anim, R.anim.popwindow_out_from_top_to_bottom);
        //overridePendingTransition(R.anim.popwindow_in, R.anim.popwindow_out_from_top_to_bottom);
        //overridePendingTransition(R.anim.popwindow_no_anim, R.anim.popwindow_out_from_bottom_to_top);
        //这里只有进入Activity2页面的Activity2的动效
        overridePendingTransition(R.anim.popwindow_in, R.anim.popwindow_no_anim);

        mBtBack = findViewById(R.id.bt_finish);
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //overridePendingTransition(R.anim.popwindow_in, R.anim.popwindow_out_from_bottom_to_top);
            }
        });
    }

}
