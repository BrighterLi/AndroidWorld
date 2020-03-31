package com.xiaoming.a002netretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.a002netretrofit.get.GetActivity;
import com.xiaoming.a002netretrofit.synget.SynGetActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGet;
    private Button btnSynGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnGet = findViewById(R.id.btn_get);
        btnSynGet = findViewById(R.id.btn_syn_get);

        btnGet.setOnClickListener(this);
        btnSynGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                startActivity(new Intent(MainActivity.this, GetActivity.class));
                break;
            case R.id.btn_syn_get:
                startActivity(new Intent(MainActivity.this, SynGetActivity.class));
                break;
        }
    }
}
