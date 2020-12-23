package com.xiaoming.androidpoints.aaajavaknowledge.initialize;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.aaajavaknowledge.initialize.staticvariable.SingleTon;
import com.xiaoming.androidpoints.aaajavaknowledge.initialize.staticvariable.SingleTon2;

public class InitializeActivity extends Activity implements View.OnClickListener{
    private Button mBtStaticVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialize);

        initView();
    }

    private void initView() {
        mBtStaticVariable = findViewById(R.id.bt_static_variable);
        mBtStaticVariable.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_static_variable:
                testStaticVariableInitialize();
                break;
        }
    }

    //Java的静态变量初始化的坑:https://www.cnblogs.com/escapist/p/8579054.html
    private void testStaticVariableInitialize() {
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("bright8#count1=" + singleTon.count1);
        System.out.println("bright8#count2=" + singleTon.count2);
        //这是就涉及到实例的初始化流程
        //1.类被加载的时候，普通方法加载到方法区，静态方法和静态字段加载到方法区中的静态区
        //2.首先静态字段会进行默认初始化。即 singTon=null       count1=0        count2=0
        //3.然后静态字段会进行显示初始化。问题就出现这里(这里就是影响结果的地方)
        //4.首先 singTon 进行显示初始化 它会创建 实例，调用构造函数,执行完成以后 此时 count1=1   count2=1
        //5.然后才是静态变量 count1和count2进行显示初始化，因为count1没有显示初始化值，所以结果就是 count1=1 但是 count2变量进行完显示初始化后值就为 1了  (在此过程之前静态变量count1和count2还没有进行显示初始化的)
        //6.然后执行静态代码块中的内容，此处没有静态代码块。
        //所以，综上 结果是 singTon.count1=1  singTon.count2=1

        SingleTon2 singleTon2 = SingleTon2.getInstance();
        System.out.println("bright8#count1=" + singleTon2.count1);
        System.out.println("bright8#count2=" + singleTon2.count2);
        //执行的结果就是 count1=1 count2=2
        //因为这种情况下， 静态变量 count2比 singTon 先完成显示初始化，结果就是我们预料到的


    }
}
