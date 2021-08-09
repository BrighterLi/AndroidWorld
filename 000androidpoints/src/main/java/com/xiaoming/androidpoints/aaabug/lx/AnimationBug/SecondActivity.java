package com.xiaoming.androidpoints.aaabug.lx.AnimationBug;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;

public class SecondActivity extends AppCompatActivity {
    private Button mBtFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second3);



        mBtFinish = findViewById(R.id.bt_finish_activity);
        mBtFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, R.anim.popwindow_out2);
            }
        });
    }
}
