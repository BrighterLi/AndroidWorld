package com.xiaoming.a010kotlin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xiaoming.a010kotlin.xiangxuedemo.modules.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBtXiangxue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getKotlinType();

        initView();

    }

    private void initView() {
        mBtXiangxue = findViewById(R.id.bt_xiangxue);
        mBtXiangxue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    //调用kotlin
    public String getKotlinType(){
        Log.i("MainActivity", "bright8#getKotlinType:type:" + new KotlinDemo().getType());
        return  new KotlinDemo().getType();
    }
}
