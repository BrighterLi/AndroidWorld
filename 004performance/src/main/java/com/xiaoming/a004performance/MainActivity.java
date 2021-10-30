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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtMemoryLeak;
    private Button mBtUiThread;
    private Button mBtBlock;

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
        mBtMemoryLeak.setOnClickListener(this);
        mBtUiThread.setOnClickListener(this);
        mBtBlock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtMemoryLeak:
                //startActivity(new Intent(MainActivity.this, MemoryLeakActivity.class));
                startActivity(new Intent(MainActivity.this, MemoryLeakActivity2.class));
                break;
            case R.id.mBtUiThread:
                startActivity(new Intent(MainActivity.this, UiThreadActivity.class));
                break;
            case R.id.mBtBlock:
                startActivity(new Intent(MainActivity.this, BlockCanaryActivity.class));
                break;
        }
    }
}
