package com.xiaoming.androidpoints.aaajavaknowledge.hook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xiaoming.androidpoints.R;

//Android插件化开发之Hook StartActivity方法：(JAVA 反射 代理 hook):https://blog.csdn.net/weixin_38503885/article/details/80653102
public class HookActivity extends AppCompatActivity {
    public static final String TAG  =  "HookActivity";
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);

        tv = (TextView)findViewById(R.id.start);
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(HookActivity.this, SecondActivity.class);
                    Bundle bundle = new Bundle();
                    Log.i(TAG, "bright8#-------------------------------->");
                    Log.i(TAG, "bright8#startActivity before");
                    Log.i(TAG, "bright8#-------------------------------->");

                    startActivity(intent, bundle);

                    Log.i(TAG, "bright8#-------------------------------->");
                    Log.i(TAG, "bright8#startActivity after");
                    Log.i(TAG, "bright8#-------------------------------->");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );
    }
}