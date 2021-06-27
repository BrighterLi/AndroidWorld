package com.widget.pullrefresh.pull_to_refresh;

import androidx.appcompat.app.AppCompatActivity;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

import android.widget.ListView;
import android.widget.ArrayAdapter;


import android.os.Bundle;

import com.widget.MainActivity;
import com.widget.R;

import java.util.ArrayList;
import java.util.List;

//【Android - 框架】之刷新加载框架Ultra-Pull-To-Refresh的使用: https://blog.csdn.net/itgungnir/article/details/53433487
public class PullToRefreshDemoActivity extends AppCompatActivity {
    private PtrClassicFrameLayout ptrLayout;
    private ListView lv;

    private ArrayAdapter<String> adapter;
    private List<String> dataSource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_demo);
        ptrLayout = (PtrClassicFrameLayout) findViewById(R.id.ptr_layout);
        lv = (ListView) findViewById(R.id.id_main_lv_lv);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initView();
        initEvent();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 初始化ListView中展示的数据
        dataSource = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            dataSource.add("Existed Old List Item " + i);
        }
    }

    /**
     * 初始化布局控件
     */
    private void initView() {
        // 初始化ListView中的数据
        adapter = new ArrayAdapter<String>(PullToRefreshDemoActivity.this, android.R.layout.simple_list_item_1, dataSource);
        lv.setAdapter(adapter);
        // 为布局设置头部和底部布局
        ptrLayout.setHeaderView(new MyPtrRefresher(PullToRefreshDemoActivity.this));
        // ptrLayout.setFooterView(new MyPtrRefresher(MainActivity.this));
        ptrLayout.addPtrUIHandler(new MyPtrHandler(PullToRefreshDemoActivity.this, ptrLayout));
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        // 为布局设置下拉刷新和上拉加载的回调事件
        ptrLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) { // 上拉加载的回调方法
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataSource.add("New Bottom List Item");
                        adapter.notifyDataSetChanged();
                        ptrLayout.refreshComplete();
                        lv.smoothScrollToPosition(dataSource.size() - 1);
                    }
                }, 1000);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) { // 下拉刷新的回调方法
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataSource.add(0, "New Top List Item");
                        adapter.notifyDataSetChanged();
                        ptrLayout.refreshComplete();
                        lv.smoothScrollToPosition(0);
                    }
                }, 1000);
            }
        });
    }

}
