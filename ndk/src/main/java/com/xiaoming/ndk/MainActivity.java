package com.xiaoming.ndk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        String strFromNDK = HelloNDK.sayHello();
        mTvShow.setText(strFromNDK);
    }

    private void initView() {
        mTvShow = findViewById(R.id.tv_show);
    }
}
