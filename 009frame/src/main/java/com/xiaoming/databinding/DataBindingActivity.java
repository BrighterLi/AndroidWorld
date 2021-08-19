package com.xiaoming.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;

import com.xiaoming.framearouter.R;
import com.xiaoming.framearouter.databinding.ActivityDataBindingBinding;

//Android官方数据绑定框架DataBinding用法详解+附带DEMO源码: https://blog.csdn.net/tyhj_sf/article/details/52065093
public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data_binding);

        //ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        //binding.setStu(new Student("loader", "山东莱芜"));
    }
}
