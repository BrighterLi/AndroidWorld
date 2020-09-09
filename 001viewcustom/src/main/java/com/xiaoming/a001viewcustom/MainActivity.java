package com.xiaoming.a001viewcustom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.a001viewcustom.imagetextbutton.ImageTextButton;
import com.xiaoming.a001viewcustom.imagetextbutton.ImageTextButtonActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        listView = findViewById(R.id.lv_main);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,demoListData);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, CircleImageViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ImageTextButtonActivity.class));
                        break;

                }
            }
        });
    }

    private void initData() {
        demoListData.add("自定义View：将图片变成圆形");
        demoListData.add("自定义View：图片和文字混合按钮");
}
}
