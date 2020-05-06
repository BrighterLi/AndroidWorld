package com.xiaoming.a001viewimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SetBackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_background);

        //activity_set_background设置了背景，则下面的设置不再生效
        getWindow().getDecorView().setBackgroundResource(R.drawable.activity_background);
    }
}
