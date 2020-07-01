package com.xiaoming.functionvideorecordingandfacerecognition.transparent;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.functionvideorecordingandfacerecognition.R;

//Android设置Activity背景为透明style的方法:https://www.jianshu.com/p/9b38dc864354
public class TransparentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
    }
}
