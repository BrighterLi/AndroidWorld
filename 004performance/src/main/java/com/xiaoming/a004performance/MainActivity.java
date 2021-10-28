package com.xiaoming.a004performance;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.a004performance.memoryleak.MemoryLeakActivity;
import com.xiaoming.a004performance.smallpoints.UiThreadActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtMemoryLeak;
    private Button mBtUiThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtMemoryLeak = findViewById(R.id.mBtMemoryLeak);
        mBtUiThread = findViewById(R.id.mBtUiThread);
        mBtMemoryLeak.setOnClickListener(this);
        mBtUiThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtMemoryLeak:
                startActivity(new Intent(MainActivity.this, MemoryLeakActivity.class));
                break;
            case R.id.mBtUiThread:
                startActivity(new Intent(MainActivity.this, UiThreadActivity.class));
                break;
        }
    }
}
