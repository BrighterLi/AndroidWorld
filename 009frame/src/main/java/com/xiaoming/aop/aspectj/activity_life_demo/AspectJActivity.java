package com.xiaoming.aop.aspectj.activity_life_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//Android使用AspectJ实现AOP -- 上手实践篇:https://blog.csdn.net/jordanhgl/article/details/104063433
import com.xiaoming.framearouter.R;

//监听Activity生命周期函数
public class AspectJActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect_j);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
