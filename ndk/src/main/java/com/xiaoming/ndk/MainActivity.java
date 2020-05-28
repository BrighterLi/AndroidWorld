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
        int add = HelloNDK.add(1, 2);
        mTvShow.setText("c：" + strFromNDK+ "\ncpp：" + add);
    }

    private void initView() {
        mTvShow = findViewById(R.id.tv_show);
    }
}
