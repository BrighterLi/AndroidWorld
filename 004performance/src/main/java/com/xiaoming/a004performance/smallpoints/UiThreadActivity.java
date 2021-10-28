package com.xiaoming.a004performance.smallpoints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.xiaoming.a004performance.R;

public class UiThreadActivity extends AppCompatActivity {
    private int num;
    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_thread);
        mTvShow = findViewById(R.id.tv_show);

        doWasteTimeWork();
    }

    //由于在主线程一直做耗时操作，会导致anr
    private void doWasteTimeWork() {
        while(true) {
            num++;
        }
    }
}
