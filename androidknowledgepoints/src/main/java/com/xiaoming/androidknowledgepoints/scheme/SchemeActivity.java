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
    private Button mSwitchBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);

        initView();
    }

    private void initView() {
        mSwitchBtn = (Button) findViewById(R.id.bt_switch);
        mSwitchBtn2 = (Button) findViewById(R.id.bt_switch2);
        mSwitchBtn3 = (Button) findViewById(R.id.bt_switch3);

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

        mSwitchBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivityBySchemeToFenqile();
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

    //跳到分期乐APP的页面,第三方APP必须已经安装
    private void switchActivityBySchemeToFenqile() {
        Uri uri=Uri.parse("fenqile://app");   //   app://test 相当于 http://www.baidu.com
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    //第三方拼多多
    //pinduoduo://com.xunmeng.pinduoduo/duo_coupon_landing.html?goods_id=133002461085&pid=10320141_139864283&cpsSign=CC_200817_10320141_139864283_27a9689746404f915b1c98f2c20cc05d&duoduo_type=2&_p_launch_id=10784_1605014712130_8ec2kgjbsc
    private void switchActivityBySchemeToPinduoduo() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("pinduoduo://com.xunmeng.pinduoduo/duo_coupon_landing.html?goods_id=133002461085&pid=10320141_139864283&cpsSign=CC_200817_10320141_139864283_27a9689746404f915b1c98f2c20cc05d&duoduo_type=2&_p_launch_id=10784_1605014712130_8ec2kgjbsc"));
        startActivity(intent);
    }
}
