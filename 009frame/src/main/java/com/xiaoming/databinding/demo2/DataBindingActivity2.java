package com.xiaoming.databinding.demo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;


import com.xiaoming.framearouter.R;
import com.xiaoming.framearouter.databinding.ActivityDataBinding2Binding;


//model变量改变自动更新数据

//Android官方数据绑定框架DataBinding用法详解+附带DEMO源码: https://blog.csdn.net/tyhj_sf/article/details/52065093
public class DataBindingActivity2 extends AppCompatActivity {
    private Student mStu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data_binding2);

        ActivityDataBinding2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding2);
        mStu = new Student("loader");
        binding.setStu(mStu);
        binding.setClick(this);
    }

    public void click(View view) {
        mStu.setName("qibin");
    }
}
