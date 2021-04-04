package com.xiaoming.a008project.im;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.xiaoming.a008project.R;

public class ImTestActivity extends AppCompatActivity {
    private EditText mEtUserId;
    private Button mBtTalking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_test);

        mEtUserId = findViewById(R.id.et_user_id);
        mBtTalking = findViewById(R.id.bt_talking);
        mBtTalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TencentImManager.initSDK(getApplication());
                String userId = mEtUserId.getText().toString();
                TencentImManager.login(userId);
                Intent intent =  new Intent();
                intent.putExtra("userSig", Integer.parseInt(mEtUserId.getText().toString()));
                intent.setClass(ImTestActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }
}
