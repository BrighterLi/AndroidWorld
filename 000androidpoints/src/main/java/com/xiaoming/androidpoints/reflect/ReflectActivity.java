package com.xiaoming.androidpoints.reflect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xiaoming.androidpoints.R;

import java.lang.reflect.Method;

//反射在Android中的应用？
//反射的机制原理？

//反射主要是指程序可以访问、检测和修改它本身状态或行为的一种能力
//反射可以让我们获得一个类的所有信息，包括私有属性和私有方法,也可以利用反射获取一些SDK对外部隐藏的API
//让对象的实例化从编译时转化为运行时,反射不能跨进程使用
//反射带来的两大弊端可能就是安全和性能问题

public class ReflectActivity extends AppCompatActivity {
    private TextView mTvReflectShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);

        initView();
        reflectGetMethod();
    }

    private void initView() {
        mTvReflectShow = findViewById(R.id.tv_reflect_show);
    }

    //通过反射获取私有方法
    private void reflectGetMethod() {
        try {
            Class<?> bookClass = Class.forName("com.xiaoming.androidpoints.reflect.Book"); //完整类名
            Object book = bookClass.newInstance(); //获得实例
            Method getAuthor = bookClass.getDeclaredMethod("getName"); //获得私有方法
            getAuthor.setAccessible(true); //调用方法前，设置访问标志
            String str = ((String) getAuthor.invoke(book)); //使用方法
            mTvReflectShow.setText("反射获取：" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
