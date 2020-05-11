package com.xiaoming.a004performanceblock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.a004performanceblock.blockcanary.TestBlockCanaryActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnBlockCanary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtnBlockCanary = findViewById(R.id.btn_blockcanary);
        mBtnBlockCanary.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_blockcanary:
                startActivity(new Intent(MainActivity.this, TestBlockCanaryActivity.class));
                break;
        }
    }
}
