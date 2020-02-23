package com.xiaoming.widgetcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//将图片变成圆形
//https://blog.csdn.net/wsyizmao/article/details/78491422
//第一步，创建你的自定义类，让它继承View类，并重写构造方法。
//第二步，重写onDraw()方法。
public class CircleImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_imag_view);
    }
}
