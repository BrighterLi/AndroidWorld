package com.xiaoming.androidpoints.serialization.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;

//序列化的应用？
//Serializable接口和Parcelable接口的区别？

//能需要让某些对象离开内存空间，存储到物理磁盘，以便长期保存，同时也能减少对内存的压力，而在需要时再将其从磁盘读取到内存，
// 比如将某个特定的对象保存到文件中，隔一段时间后再把它读取到内存中使用，那么该对象就需要实现序列化操作

public class SerializationActivity extends AppCompatActivity {
    private Button mBtParcelabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialization);

        initView();
    }

    private void initView() {
        mBtParcelabel = findViewById(R.id.bt_parcelable);

        mBtParcelabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SerializationActivity.this, UnSerializationActivity.class);
                Person person = new Person("xiaoming", "123456");
                Bundle bundle = new Bundle();
                //Parcelable序列化
                bundle.putParcelable("person", person); //直接传递对象
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
