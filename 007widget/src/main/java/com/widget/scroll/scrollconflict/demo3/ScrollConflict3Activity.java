package com.widget.scroll.scrollconflict.demo3;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.widget.R;
import com.widget.scroll.scrollconflict.demo3.adapter.ListViewAdapter;
import com.widget.scroll.scrollconflict.demo3.adapter.ViewPagerAdapter;
import com.widget.scroll.scrollconflict.demo3.view.BottomScrollView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//https://www.jianshu.com/p/87a41b8c0dd0
//SwipeRefreshLayout+ScrollView+ViewPager+ListView
// 上下滑动+左右滑动；内部拦截法

public class ScrollConflict3Activity extends AppCompatActivity {

    /**
     * viewpager横向滑动的阈值
     */
    private static final int THRESHOLD_X_VIEW_PAGER = 60;
    /**
     * listview竖向滑动的阈值
     */
    private static final int THRESHOLD_Y_LIST_VIEW = 20;

    /**
     * 下拉刷新控件
     */
    private SwipeRefreshLayout mRefreshLayout;
    private BottomScrollView mScrollView;
    private ViewPager mViewPager;
    private ListView mListView;

    private ViewPagerAdapter mViewPagerAdapter;
    private ListViewAdapter mListViewAdapter;

    private boolean isSvToBottom = false;

    private float mLastX;
    private float mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_conflict3);

        initView();
        initAction();
        fixSlideConflict();
    }
    private void initView() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_refresh_layout);
        mScrollView = (BottomScrollView) findViewById(R.id.main_scroll_view);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mListView = (ListView) findViewById(R.id.main_list_view);

        //ListViewAdapter:context;data
        mListViewAdapter = new ListViewAdapter( this, getListViewData());
        mListView.setAdapter(mListViewAdapter);

        //ViewPagerAdapter:context;data
        mViewPagerAdapter = new ViewPagerAdapter(this, getViewPagerData());
        mViewPager.setAdapter(mViewPagerAdapter);

    }

    private List<String> getListViewData() {
        List<String> data = new ArrayList<>();
        for(int i = 1; i <= 20; i ++) {
            data.add(i + " item");
        }
        return data;
    }

    private List<String> getViewPagerData() {
        List<String> data = new ArrayList<>();
        for(int i = 1; i <= 5; i ++) {
            data.add("page " + i);
        }
        return data;
    }

    private void initAction() {
        //模拟下拉刷新，0.5秒后，下拉刷新状态视图消失
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });

        mScrollView.setScrollToBottomListener(new BottomScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollToBottom() {
                isSvToBottom = true;
            }

            @Override
            public void onNotScrollToBottom() {
                isSvToBottom = false;
            }
        });
    }

    private void fixSlideConflict() {
        // ViewPager滑动冲突解决
        //内部拦截法
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if(action == MotionEvent.ACTION_DOWN) {
                    // 记录点击到ViewPager时候，手指的X坐标
                    mLastX = event.getX();
                }
                if(action == MotionEvent.ACTION_MOVE) {
                    // 超过阈值，禁止SwipeRefreshLayout下拉刷新，禁止ScrollView截断点击事件
                    if(Math.abs(event.getX() - mLastX) > THRESHOLD_X_VIEW_PAGER) {
                        mRefreshLayout.setEnabled(false);
                        mScrollView.requestDisallowInterceptTouchEvent(true);
                    }
                }
                // 用户抬起手指，恢复父布局状态
                if(action == MotionEvent.ACTION_UP) {
                    mRefreshLayout.setEnabled(true);
                    mScrollView.requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });

        // ListView滑动冲突解决
        //内部拦截法
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if(action == MotionEvent.ACTION_DOWN) {
                    mLastY = event.getY();
                }
                if(action == MotionEvent.ACTION_MOVE) {
                    int top = mListView.getChildAt(0).getTop();
                    float nowY = event.getY();
                    if(!isSvToBottom) {
                        // 允许scrollview拦截点击事件, scrollView滑动
                        mScrollView.requestDisallowInterceptTouchEvent(false);
                    } else if(top == 0 && nowY - mLastY > THRESHOLD_Y_LIST_VIEW) {
                        // 允许scrollview拦截点击事件, scrollView滑动
                        mScrollView.requestDisallowInterceptTouchEvent(false);
                    } else {
                        // 不允许scrollview拦截点击事件， listView滑动
                        mScrollView.requestDisallowInterceptTouchEvent(true);
                    }
                }
                return false;
            }
        });
    }
}
