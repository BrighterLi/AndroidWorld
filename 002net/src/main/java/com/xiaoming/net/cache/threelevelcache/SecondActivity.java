package com.xiaoming.net.cache.threelevelcache;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.net.R;


public class SecondActivity extends Activity {
    private Button mBtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mBtBack = findViewById(R.id.bt_back);
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
