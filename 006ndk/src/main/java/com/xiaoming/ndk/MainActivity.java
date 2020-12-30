package com.xiaoming.ndk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoming.ndk.solib.SoLibEntranceActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mTvShow;
    private Button mBtSoLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        String strFromNDK = HelloNDK.sayHello();
        int add = HelloNDK.add(1, 2);
        mTvShow.setText("c：" + strFromNDK+ "\ncpp：" + add);

        mBtSoLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SoLibEntranceActivity.class));
            }
        });
    }

    private void initView() {
        mBtSoLib = findViewById(R.id.bt_so_lib);
        mTvShow = findViewById(R.id.tv_show);
    }
}
