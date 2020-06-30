package com.xiaoming.androidknowledgepoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.androidknowledgepoints.broadcast.LocalBroadcastReceiverActivity;
import com.xiaoming.androidknowledgepoints.innerclass.InnerClassAndVariableActivity;
import com.xiaoming.androidknowledgepoints.jnidemo.JniActivity;
import com.xiaoming.androidknowledgepoints.jnidemo2.Jni2Activity;
import com.xiaoming.androidknowledgepoints.json.JsonActivity;
import com.xiaoming.androidknowledgepoints.reflect.ReflectActivity;
import com.xiaoming.androidknowledgepoints.regularexpression.RegularExpressionActivity;
import com.xiaoming.androidknowledgepoints.serialization.GoToActivity;
import com.xiaoming.androidknowledgepoints.serialization.parcelable.SerializationActivity;
import com.xiaoming.androidknowledgepoints.solib.SoLibEntranceActivity;
import com.xiaoming.encryption.EncryptionActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mLvMain;
    private List<String> demoDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoData();
        initView();
    }

    private void initView() {
        mLvMain = findViewById(R.id.lv_main);
        mLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, JniActivity.class));
                        break;
                    case 1:
                    startActivity(new Intent(MainActivity.this, JniActivity.class));
                    break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Jni2Activity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, InnerClassAndVariableActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, JsonActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, LocalBroadcastReceiverActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, ReflectActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, GoToActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, RegularExpressionActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, EncryptionActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, SoLibEntranceActivity.class));
                        break;
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demoDataList);
        mLvMain.setAdapter(adapter);
    }

    private void initDemoData() {
        demoDataList = new ArrayList<>();
        demoDataList.add("Util");
        demoDataList.add("jni：加减法");
        demoDataList.add("jni:hello world");
        demoDataList.add("java内部类访问局部变量时局部变量必须声明为final");
        demoDataList.add("Json");
        demoDataList.add("LocalBroadcast");
        demoDataList.add("反射");
        demoDataList.add("序列化");
        demoDataList.add("正则");
        demoDataList.add("加密解密");
        demoDataList.add("调用so库的方法");

    }
}
