package com.xiaoming.a004performance.block.blockcanary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.xiaoming.a004performance.R;

public class BlockCanaryActivity extends AppCompatActivity {
    private Button mBtnBlockTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_canary);
        initView();
    }

    private void initView() {
        mBtnBlockTest = findViewById(R.id.btn_block_test);
        mBtnBlockTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testBlockCanary(view);
            }
        });
    }

    //卡顿测试
    public void testBlockCanary(View view) {
        SystemClock.sleep(10000);
        System.out.println("处理其他业务逻辑");
    }


}
