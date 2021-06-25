package com.widget.animation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.widget.R;

import android.view.View;
import android.widget.Button;

//Android 动画之Translate: https://blog.csdn.net/u010507199/article/details/47075687
//Android Activity跳转动画 - overridePendingTransition用法及原理分析: https://blog.csdn.net/ccpat/article/details/84883418
//http://www.360doc.com/content/18/0602/16/11813302_759107458.shtml
public class Activity1 extends AppCompatActivity {
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        //overridePendingTransition(R.anim.popwindow_no_anim, R.anim.popwindow_out_from_bottom_to_top);
        //overridePendingTransition(0, 0);
        mBt = findViewById(R.id.bt_jump);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.popwindow_no_anim, R.anim.popwindow_out_from_bottom_to_top);
                startActivity(new Intent(Activity1.this, Activity2.class));
            }
        });
    }
}
