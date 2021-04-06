package com.xiaoming.androidpoints.broadcast;

import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//https://www.cnblogs.com/lihaolihao/p/4702541.html
import com.xiaoming.androidpoints.R;

public class LocalBroadcastActivity extends AppCompatActivity {
    private Button mBtnSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast);

        initView();
    }

    private void initView() {
        mBtnSendBroadcast = findViewById(R.id.bt_send_broadcast);
        mBtnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(LocalBroadcastActivity.this);
                Intent intent = new Intent();
                intent.setAction(LocalBroadcastReceiverActivity.FILETER);
                intent.putExtra("name", "我是LocalBroadcast发送");
                localBroadcastManager.sendBroadcast(intent);
                //startActivity(new Intent(LocalBroadcastActivity.this, LocalBroadcastReceiverActivity.class));
            }
        });
    }
}
