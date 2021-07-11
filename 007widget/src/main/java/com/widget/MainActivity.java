package com.widget;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.widget.aaaview.ViewKownledgePointsActivity;
import com.widget.banner.BannerTestActivity;
import com.widget.dialog.DialogTestActivity;
import com.widget.horizontalscrollview.TabStripActivity;
import com.widget.image.ImageTestActivity;
import com.widget.keyboard.KeyboardEntranceActivity;
import com.widget.listview.backtotop.BackToTopActivity;
import com.widget.listview.listviewdemo.demo1.ListView1Activity;
import com.widget.listview.listviewdemo.demo2.ListView2Activity;
import com.widget.listview.listviewdemo.demo3.ListView3Activity;
import com.widget.loading.LoadingDemoActivity;
import com.widget.material_design.MaterialDesignActivity;
import com.widget.pullrefresh.PullRefreshActivity;
import com.widget.pullrefresh.pull_to_refresh.PullToRefreshDemoActivity;
import com.widget.recyclerview.RecyclerViewActivity;
import com.widget.recyclerview.ceiling.CeilingActivity;
import com.widget.scroll.doublescroll.scrollviewrecyclerview.ScrollviewActivity;
import com.widget.scroll.scrollconflict.demo1.ScrollConflictActivity;
import com.widget.scroll.scrollconflict.demo2.ScrollConflict2Activity;
import com.widget.scroll.scrollconflict.demo3.ScrollConflict3Activity;
import com.widget.statusbar.StatusBarTestActivity;
import com.widget.animation.AnimationTestActivity;
import com.widget.textview.TextViewTestActivity;
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
        mDataList.add("View知识点");
        mDataList.add("视频"); //0
        mDataList.add("输入键盘"); //1
        mDataList.add("下拉刷新"); //2
        mDataList.add("ScrollView"); //3
        mDataList.add("通過HorizontalScrollView自定义TabLayout"); //4
        mDataList.add("滑动冲突:ScrollView+ListView;同向，内部拦截法"); //5
        mDataList.add("滑动冲突：ViewPager+ListView;  外部左右+内部上下;外部拦截法+内部拦截法"); //6
        mDataList.add("滑动冲突：SwipeRefreshLayout+ScrollView+ViewPager+ListView；上下滑动+左右滑动；内部拦截法"); //7
        mDataList.add("ListView:分页请求"); //8
        mDataList.add("ViewPager"); //9
        mDataList.add("ListView:点击悬浮按钮回到顶部"); //10

        mDataList.add("Dialog:全屏dialog"); //11
        mDataList.add("Image"); //12
        mDataList.add("状态栏"); //13
        mDataList.add("RecyclerView"); //14
        mDataList.add("ListView 基本用法"); //15
        mDataList.add("ListView 自定义Item"); //16
        mDataList.add("ListView 优化"); //17
        mDataList.add("loading"); //18
        mDataList.add("Banner"); //19
        mDataList.add("animation动画"); //20

        mDataList.add("TextView"); //21
        mDataList.add("Material Design"); //22
        mDataList.add("上拉刷新下拉加载框架Ultra-Pull-To-Refresh"); //23

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
                        startActivity(new Intent(MainActivity.this, ViewKownledgePointsActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, VideoActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, KeyboardEntranceActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, PullRefreshActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ScrollviewActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, TabStripActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, ScrollConflictActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, ScrollConflict2Activity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, ScrollConflict3Activity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, PagingRequestActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, BackToTopActivity.class));
                        break;

                    case 12:
                        startActivity(new Intent(MainActivity.this, DialogTestActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, ImageTestActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, StatusBarTestActivity.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                        break;
                        case 16:
                        startActivity(new Intent(MainActivity.this, ListView1Activity.class));
                        break;
                    case 17:
                        startActivity(new Intent(MainActivity.this, ListView2Activity.class));
                        break;
                    case 18:
                        startActivity(new Intent(MainActivity.this, ListView3Activity.class));
                        break;
                    case 19:
                        startActivity(new Intent(MainActivity.this, LoadingDemoActivity.class));
                        break;
                    case 20:  //Banner
                        startActivity(new Intent(MainActivity.this, BannerTestActivity.class));
                        break;
                    case 21:  //动画
                        startActivity(new Intent(MainActivity.this, AnimationTestActivity.class));
                        break;

                    case 22:  //TextView
                        startActivity(new Intent(MainActivity.this, TextViewTestActivity.class));
                        break;
                    case 23:  //MaterialDesign
                        startActivity(new Intent(MainActivity.this, MaterialDesignActivity.class));
                        break;

                    case 24:  //刷新加载框架Ultra-Pull-To-Refresh
                        startActivity(new Intent(MainActivity.this, PullToRefreshDemoActivity.class));
                        break;

                }
            }
        });
    }
}
