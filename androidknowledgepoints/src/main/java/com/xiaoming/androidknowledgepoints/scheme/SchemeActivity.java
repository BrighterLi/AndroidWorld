package com.xiaoming.androidknowledgepoints.scheme;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidknowledgepoints.R;

public class SchemeActivity extends Activity {
    private Button mSwitchBtn;
    private Button mSwitchBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);

        initView();
    }

    private void initView() {
        mSwitchBtn = (Button) findViewById(R.id.bt_switch);
        mSwitchBtn2 = (Button) findViewById(R.id.bt_switch2);

        mSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivityByScheme();
            }
        });

        mSwitchBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivityByScheme2();
            }
        });
    }

    //跳到APP内的某个页面，隐式跳转
    private void switchActivityByScheme() {
        Uri uri=Uri.parse("app://test");   //   app://test 相当于 http://www.baidu.com
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    //跳到第三方APP的页面,第三方APP必须已经安装
    private void switchActivityByScheme2() {
        Uri uri=Uri.parse("app2://test2");   //   app://test 相当于 http://www.baidu.com
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
