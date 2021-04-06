package com.xiaoming.a004performance;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.a004performance.memoryleak.MemoryLeakActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtMemoryLeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtMemoryLeak = findViewById(R.id.bt_memory_leak);
        mBtMemoryLeak.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_memory_leak:
                startActivity(new Intent(MainActivity.this, MemoryLeakActivity.class));
                break;
        }
    }
}
