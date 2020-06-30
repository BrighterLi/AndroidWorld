package com.xiaoming.ndk.solib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoming.ndk.HelloNDK;
import com.xiaoming.ndk.R;

//调用so库，貌似和jni调用方式一模一样
public class SoLibEntranceActivity extends AppCompatActivity {
    private Button mBtShowSoLib;
    private TextView mTvShowSoLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_lib_entrance);

        initView();
    }

    private void initView() {
        mBtShowSoLib = findViewById(R.id.bt_show_so_lib);
        mTvShowSoLib = findViewById(R.id.tv_show_so_lib);

        mBtShowSoLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = HelloNDK.sayHello();
                mTvShowSoLib.setText(str);
            }
        });
    }
}
