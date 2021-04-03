package com.widget.scroll.scrollconflict.demo2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.widget.R;
//ViewPager+ListView；外部左右滑动，内部上下滑
//外部拦截法；内部拦截法

//https://blog.csdn.net/weixin_44965650/article/details/107604646
public class ScrollConflict2Activity extends AppCompatActivity {
    private MyListView listView1, listView2, listView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_conflict2);

        listView1 = findViewById(R.id.lv1);
        listView2 = findViewById(R.id.lv2);
        listView3 = findViewById(R.id.lv3);

        String[] data = {"刘能", "王大拿", "赵玉田", "谢永强", "王老七", "赵四", "王小蒙", "谢广坤", "刘大脑袋", "谢大脚", "王长贵", "刘英", "谢腾飞", "王云" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        //为ListView设置适配器
        listView1.setAdapter(adapter);
        listView2.setAdapter(adapter);
        listView3.setAdapter(adapter);
    }
}
