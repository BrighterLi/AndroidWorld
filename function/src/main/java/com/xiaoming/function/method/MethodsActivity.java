package com.xiaoming.function.method;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.function.MainActivity;
import com.xiaoming.function.R;

import java.util.ArrayList;
import java.util.List;


//各种功能Demo
public class MethodsActivity extends AppCompatActivity {
    private ListView demoListView;
    private List<String> demoListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_methods);

        initData();
        demoListView = findViewById(R.id.lv_main);
        demoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MethodsActivity.this, IsRootActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MethodsActivity.this, IsVoLteEnabledActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MethodsActivity.this, StatusBarActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MethodsActivity.this, PhoneOperatorActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MethodsActivity.this, NetworkStrengthActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MethodsActivity.this, FastClickActivity.class));
                        break;
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,demoListData);
        demoListView.setAdapter(adapter);
    }

    private void initData() {
        demoListData.add("判断安卓设备是否root");
        demoListData.add("获取VoLte开关开关状态");
        demoListData.add("获取状态栏高度，改变状态栏颜色");
        demoListData.add("获取手机运营商");
        demoListData.add("获取信号强度");
        demoListData.add("防止快速重复点击");
    }
}
