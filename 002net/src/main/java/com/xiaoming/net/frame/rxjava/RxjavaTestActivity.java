package com.xiaoming.net.frame.rxjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.net.R;
import com.xiaoming.net.frame.rxjava.download.DownloadActivity;

public class RxjavaTestActivity extends Activity implements View.OnClickListener{
    private Button mBtRxjava1;
    private Button mBtRxjava2;
    private Button mBtRxjava3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_test);

        mBtRxjava1 = findViewById(R.id.bt_rxjava1);
        mBtRxjava2 = findViewById(R.id.bt_rxjava2);
        mBtRxjava3 = findViewById(R.id.bt_rxjava3);
        mBtRxjava1.setOnClickListener(this);
        mBtRxjava2.setOnClickListener(this);
        mBtRxjava3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rxjava1:
                startActivity(new Intent(RxjavaTestActivity.this, RxJavaDemoActivity.class));
                break;
            case R.id.bt_rxjava2:
                startActivity(new Intent(RxjavaTestActivity.this, RxJavaDemo2Activity.class));
                break;
            case R.id.bt_rxjava3:
                startActivity(new Intent(RxjavaTestActivity.this, DownloadActivity.class));
                break;
        }
    }
}
