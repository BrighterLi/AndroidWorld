package com.xiaoming.a004performance;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.a004performance.block.blockcanary.BlockCanaryActivity;

import com.xiaoming.a004performance.memory.memoryleak.MemoryLeakActivity;
import com.xiaoming.a004performance.memory.memoryleak.MemoryLeakActivity2;
import com.xiaoming.a004performance.smallpoints.UiThreadActivity;
import com.xiaoming.a004performance.tool.strictmode.StrictModeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtMemoryLeak;
    private Button mBtUiThread;
    private Button mBtBlock;
    private Button mBtStrictMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtMemoryLeak = findViewById(R.id.mBtMemoryLeak);
        mBtUiThread = findViewById(R.id.mBtUiThread);
        mBtBlock = findViewById(R.id.mBtBlock);
        mBtStrictMode = findViewById(R.id.mBtStrictMode);

        mBtMemoryLeak.setOnClickListener(this);
        mBtUiThread.setOnClickListener(this);
        mBtBlock.setOnClickListener(this);
        mBtStrictMode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtMemoryLeak:
                //leakcanary内存泄漏
                //startActivity(new Intent(MainActivity.this, MemoryLeakActivity.class));
                //非静态内部类导致内存泄漏
                startActivity(new Intent(MainActivity.this, MemoryLeakActivity2.class));
                break;
            case R.id.mBtUiThread:
                startActivity(new Intent(MainActivity.this, UiThreadActivity.class));
                break;
            case R.id.mBtBlock:
                startActivity(new Intent(MainActivity.this, BlockCanaryActivity.class));
                break;
            case R.id.mBtStrictMode:
                startActivity(new Intent(MainActivity.this, StrictModeActivity.class));
                break;
        }
    }
}
