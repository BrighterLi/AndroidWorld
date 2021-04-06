package com.xiaoming.androidpoints.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;

public class LocalBroadcastReceiverActivity extends AppCompatActivity {
    public static final String FILETER = "LOCAL_BROADCAST";
    private BroadcastReceiver mBroadcastReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast_receiver);

        initView();
    }

    private void initView() {
        mBt = findViewById(R.id.bt);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocalBroadcastReceiverActivity.this, LocalBroadcastActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FILETER);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) { //接收广播
                String name = intent.getStringExtra("name");
                Log.e("LocalBroadcastReceiver", "bright#name=" + name);
            }
        };
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter); //注册广播
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }
}
