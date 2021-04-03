package com.a006designmode.creationmode.buidermode2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.a006designmode.R;

//《Android源码设计模式》之Builder模式:https://blog.csdn.net/qq_16240393/article/details/77602880

//Product产品类—产品的抽象类；
//Builder—抽象Builder类，规范产品的组建，一般是由子类实现具体的组建过程；
//ConcreteBuilder—具体的Builder类；
//Director—统一组装过程。
public class CreationBuilder2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_builder2);

        // 测试代码
        Builder builder = new MabBookBuilder();
        Director director = new Director(builder);
        director.construct("华硕主板", "飞利浦显示器", "Mac OX X 10.10");
        Computer computer = builder.create();
        Log.i("GuiFa", "计算机信息: " + computer);
    }
}
