package com.xiaoming.androidknowledgepoints.jnidemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xiaoming.androidknowledgepoints.R;

//https://www.jianshu.com/p/b4431ac22ec2
public class Jni2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni2);

        String text = NDKTools.getStringFromNDK();
        ((TextView)findViewById(R.id.tv_show)).setText(text);
    }
}
