package com.xiaoming.a004performance.memory.memoryleak;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoming.a004performance.R;

//https://www.cnblogs.com/Jack-cx/p/10190739.html

public class MemoryLeakActivity extends AppCompatActivity {
    EditText name;   //用户名
    EditText pass;    //密码    //内存泄漏
    private static TestResource mResource = null; //mResource是静态变量，app整个生命周期内都存在，所以也一直在持有着外部类的对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        //内存泄漏
        if(mResource == null){
            mResource = new TestResource();
            mResource = new TestResource();
            mResource = new TestResource();
            mResource = new TestResource();

        }//

        name = (EditText) findViewById(R.id.name);  //获取用户名
        pass = (EditText) findViewById(R.id.pass);  //获取密码
    }

    public void Check(View view) {
        String mname = "du";
        String mpass = "du";
        String user = name.getText().toString().trim();
        String pwd = pass.getText().toString().trim();
        if (user.equals(mname) && pwd.equals(mpass)) {
            Toast.makeText(this,
                    "密码正确", Toast.LENGTH_SHORT).show();
            if(mResource == null){
                mResource = new TestResource();
            }
        } else {
            Toast.makeText(this,
                    "密码错误", Toast.LENGTH_SHORT).show();

        }
    }    //内存泄漏，非静态内部类，持有外部类的引用
    class TestResource {
        //
    }//
}
