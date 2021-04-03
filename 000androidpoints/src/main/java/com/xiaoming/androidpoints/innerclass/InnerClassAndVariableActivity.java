package com.xiaoming.androidpoints.innerclass;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.androidpoints.R;

//java内部类访问局部变量时局部变量必须声明为final
public class InnerClassAndVariableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_class_and_variable);

        function();
    }

    //在局部变量没有重新赋值的情况下，它默认局部变量为final型，认为你只是忘记加final声明了而已。
    // 如果你重新给局部变量改变了值或引用，那就无法默认为final了，就会报错
    public void function() {
        final int a = 10; //方法局部变量
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(a); //内部类访问局部变量a
            }
        }).start();
    }
}
