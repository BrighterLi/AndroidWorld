package com.xiaoming.net.pingnet2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.net.R;

public class PingNetActivity2 extends AppCompatActivity {
    private Button mBtDetectNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_net2);

        initView();
    }

    private void initView() {
        mBtDetectNet = findViewById(R.id.bt_net_detect);
        mBtDetectNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkDetector.startDetectAndReport();
            }
        });
    }
}
