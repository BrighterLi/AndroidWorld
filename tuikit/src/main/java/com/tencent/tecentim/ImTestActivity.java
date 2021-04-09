package com.tencent.tecentim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tencent.tecentim.callback.LoginCallBack;

public class ImTestActivity extends AppCompatActivity {
    private EditText mEtUserId;
    private EditText mEtOtherUserId;
    private Button mBtTalking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_test);
        TencentImManager.initSDK(getApplication());

        mEtUserId = findViewById(R.id.et_user_id);
        mEtOtherUserId = findViewById(R.id.et_other_user_id);
        mBtTalking = findViewById(R.id.bt_talking);
        mBtTalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自己的UserId
                String userId = mEtUserId.getText().toString();
                Log.i("ChatActivity", "bright8#自己的userId:" + userId);
                TencentImManager.login(userId, new LoginCallBack() {
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent();
                        //聊天对象的UserId
                        String userOtherId = mEtOtherUserId.getText().toString();
                        intent.putExtra("userId", userOtherId);
                        intent.setClass(ImTestActivity.this, ChatActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}