package com.widget.horizontalscrollview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.widget.R;

import java.util.ArrayList;
import java.util.Collections;

//https://blog.csdn.net/weixin_41101173/article/details/80158169
public class TabStripActivity extends AppCompatActivity {
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout container;
    private String cities[] = new String[]{"London", "Bangkok", "Paris", "Dubai", "Istanbul", "New York","Singapore", "Kuala Lumpur", "Hong Kong", "Tokyo", "Barcelona", "Vienna", "Los Angeles", "Prague", "Rome", "Seoul", "Mumbai", "Jakarta", "Berlin", "Beijing", "Moscow", "Taipei", "Dublin", "Vancouver"};
    private ArrayList<String> data = new ArrayList<>();
    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_strip);

        bindData();
        setUIRef();
        bindHZSWData();
    }

    //将集合中的数据绑定到HorizontalScrollView上。LinearLayout里面动态放入一个个TextView
    private void bindHZSWData() {    //为布局中textview设置好相关属性
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(20, 10, 20, 10);
        for (int i = 0; i < data.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(data.get(i));
            textView.setTextColor(Color.WHITE);
            textView.setLayoutParams(layoutParams);
            container.addView(textView);
            container.invalidate();
        }

    }

    //初始化布局中的控件
    private void setUIRef() {
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        container = (LinearLayout) findViewById(R.id.horizontalScrollViewItemContainer);
        testTextView = (TextView) findViewById(R.id.testTextView);
    }

    //将字符串数组与集合绑定起来
    private void bindData() {
        //add all cities to our ArrayList
        Collections.addAll(data, cities);
    }
}