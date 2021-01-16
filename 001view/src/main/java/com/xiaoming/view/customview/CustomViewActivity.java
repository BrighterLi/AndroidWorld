package com.xiaoming.view.customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.view.R;
import com.xiaoming.view.customview.circleview.CircleImageViewActivity;
import com.xiaoming.view.customview.clock.ClockActivity;
import com.xiaoming.view.customview.imagetextbutton.ImageTextButtonActivity;
import com.xiaoming.view.customview.input.InputActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomViewActivity extends Activity {
    private ListView listView;
    private List<String> demoListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        initData();
        listView = findViewById(R.id.lv_main);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoListData);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(CustomViewActivity.this, CircleImageViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(CustomViewActivity.this, ImageTextButtonActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(CustomViewActivity.this, InputActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(CustomViewActivity.this, ClockActivity.class));
                        break;

                }
            }
        });
    }

    private void initData() {
        demoListData.add("自定义View：将图片变成圆形");
        demoListData.add("自定义View：图片和文字混合按钮");
        demoListData.add("自定义View：输入");
        demoListData.add("自定义View：时钟");
    }
}
