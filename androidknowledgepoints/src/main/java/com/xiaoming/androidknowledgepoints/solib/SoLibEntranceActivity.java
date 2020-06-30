package com.xiaoming.androidknowledgepoints.solib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xiaoming.androidknowledgepoints.R;
import com.xiaoming.ndk.HelloNDK;

public class SoLibEntranceActivity extends AppCompatActivity {
    private TextView mTvShowSoLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_lib_entrance);

        initView();
    }

    private void initView() {
        mTvShowSoLib = findViewById(R.id.tv_show_so_lib);
        //HelloNDK类的包名必须与so库创建时HelloNDK类的包名相同
        String str = HelloNDK.sayHello();
        mTvShowSoLib.setText(str);
    }
}
