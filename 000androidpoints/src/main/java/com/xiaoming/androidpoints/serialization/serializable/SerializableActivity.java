package com.xiaoming.androidpoints.serialization.serializable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xiaoming.androidpoints.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//java.io.FileNotFoundException: /serializable/user.txt (No such file or directory) ?
public class SerializableActivity extends AppCompatActivity {
    private TextView mTvShowSerializable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable);
        Log.d("SerializableActivity", "bright#onCreate");

        mTvShowSerializable = findViewById(R.id.tv_show_serializable);
        try {
            doSerializable();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SerializableActivity", "bright#e：" + e);
        }
    }

    private void doSerializable() throws Exception{
        Log.d("SerializableActivity", "bright#doSerializable");
        //构造对象
        User user = new User();
        user.setId(111);
        user.setName("bright");

        //把对象序列化到文件
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("/serializable/user.txt"));
        outputStream.writeObject(user);
        outputStream.close();

        //反序列化到内存
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("/serializable/user.txt"));
        User user2 = ((User) inputStream.readObject());
        int id = user2.getId();
        String name = user2.getName();
        mTvShowSerializable.setText("反序列得到name：" + name);
        Log.d("SerializableActivity", "bright#doSerializable#name：" + name);
        inputStream.close();
    }
}
