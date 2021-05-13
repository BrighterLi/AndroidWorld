package com.xiaoming.net.frame.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.net.R;
import com.xiaoming.net.frame.retrofit.asyncget2.AsyncGet2Activity;
import com.xiaoming.net.frame.retrofit.asynget.GetActivity;
import com.xiaoming.net.frame.retrofit.get3.Get3Activity;
import com.xiaoming.net.frame.retrofit.post.PostActivity;
import com.xiaoming.net.frame.retrofit.post2.Post2Activity;
import com.xiaoming.net.frame.retrofit.synget.SynGetActivity;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnGet;
    private Button btnAsyncGet2;
    private Button btnSynGet;
    private Button btnGet3;
    private Button btnPost;
    private Button btnPost2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initView();
    }

    private void initView() {
        btnGet = findViewById(R.id.btn_get);
        btnAsyncGet2 = findViewById(R.id.btn_asyn_get2);
        btnSynGet = findViewById(R.id.btn_syn_get);
        btnGet3 = findViewById(R.id.btn_get3);
        btnPost = findViewById(R.id.btn_post);
        btnPost2 = findViewById(R.id.btn_post2);

        btnGet.setOnClickListener(this);
        btnAsyncGet2.setOnClickListener(this);
        btnSynGet.setOnClickListener(this);
        btnGet3.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnPost2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                startActivity(new Intent(RetrofitActivity.this, GetActivity.class));
                break;
            case R.id.btn_asyn_get2:
                startActivity(new Intent(RetrofitActivity.this, AsyncGet2Activity.class));
                break;
            case R.id.btn_syn_get:
                startActivity(new Intent(RetrofitActivity.this, SynGetActivity.class));
                break;
            case R.id.btn_get3:
                startActivity(new Intent(RetrofitActivity.this, Get3Activity.class));
                break;
            case R.id.btn_post:
                startActivity(new Intent(RetrofitActivity.this, PostActivity.class));
                break;
            case R.id.btn_post2:
                startActivity(new Intent(RetrofitActivity.this, Post2Activity.class));
                break;
        }
    }
}

