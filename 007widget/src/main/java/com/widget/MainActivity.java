package com.widget;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.widget.dialog.DialogTestActivity;
import com.widget.dialog.FullScreenDialog;
import com.widget.horizontalscrollview.TabStripActivity;
import com.widget.keyboard.KeyboardEntranceActivity;
import com.widget.listview.backtotop.BackToTopActivity;
import com.widget.pullrefresh.PullRefreshActivity;
import com.widget.scroll.doublescroll.scrollviewrecyclerview.ScrollviewActivity;
import com.widget.scroll.scrollconflict.demo1.ScrollConflictActivity;
import com.widget.scroll.scrollconflict.demo2.ScrollConflict2Activity;
import com.widget.scroll.scrollconflict.demo3.ScrollConflict3Activity;
import com.widget.video.VideoActivity;
import com.widget.listview.pagingrequest.PagingRequestActivity;
import com.widget.viewpager.ViewPagerActivity;

import java.util.ArrayList;
import java.util.List;

//https://www.cnblogs.com/androidez/archive/2013/02/09/2909665.html

public class MainActivity extends AppCompatActivity {
    private ListView mLvMain;
    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoData();
        initView();
    }

    private void initDemoData() {
        mDataList = new ArrayList<>();
        mDataList.add("视频");
        mDataList.add("输入键盘");
        mDataList.add("下拉刷新");
        mDataList.add("ScrollView");
        mDataList.add("通過HorizontalScrollView自定义TabLayout");
        mDataList.add("滑动冲突:ScrollView+ListView;同向，内部拦截法");
        mDataList.add("滑动冲突：ViewPager+ListView;  外部左右+内部上下;外部拦截法+内部拦截法");
        mDataList.add("滑动冲突：SwipeRefreshLayout+ScrollView+ViewPager+ListView；上下滑动+左右滑动；内部拦截法");
        mDataList.add("ListView:分页请求");
        mDataList.add("ViewPager");
        mDataList.add("ListView:点击悬浮按钮回到顶部");
        mDataList.add("Dialog:全屏dialog");
    }

    private void initView() {
        mLvMain = findViewById(R.id.lv_main);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDataList);
        mLvMain.setAdapter(arrayAdapter);
        mLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, VideoActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, KeyboardEntranceActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, PullRefreshActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ScrollviewActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, TabStripActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, ScrollConflictActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, ScrollConflict2Activity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, ScrollConflict3Activity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, PagingRequestActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, BackToTopActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, DialogTestActivity.class));
                        break;
                }
            }
        });
    }
}
