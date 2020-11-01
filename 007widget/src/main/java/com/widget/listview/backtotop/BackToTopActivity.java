package com.widget.listview.backtotop;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.widget.R;

import java.util.ArrayList;
import java.util.List;


//一键回到列表顶部
//Android悬浮按钮点击返回顶部FloatingActionButton:https://www.jb51.net/article/106644.htm
public class BackToTopActivity extends AppCompatActivity {
    private ListView mListView;
    private FloatingActionButton mFloatBtn;
    private MyListViewAdapter mAdapter;
    private List<String> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_to_top);

        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview_main);
        mFloatBtn = (FloatingActionButton) findViewById(R.id.floating_btn_main);
        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add(i + "");
        }
        mAdapter = new MyListViewAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        //悬浮按钮的点击事件的监听
        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listView返回到顶部
                mListView.smoothScrollToPosition(0);
            }
        });
    }

}
