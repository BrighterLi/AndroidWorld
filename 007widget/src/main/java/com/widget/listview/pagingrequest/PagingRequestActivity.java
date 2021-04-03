package com.widget.listview.pagingrequest;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.widget.R;


//分页加载数据Demo: https://www.cnblogs.com/androidez/archive/2013/02/09/2909665.html

//adapter.notifyDataSetChanged()动态更新ListView
public class PagingRequestActivity extends AppCompatActivity {
    private static final int SUCCESS_GET_DATA = 0;
    private ListView listview;
    private DataService service;
    private List<String> data; // 加载的总数据
    private ArrayAdapter<String> adapter;

    private boolean finish = true; // 是否加载完成
    private View footer;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS_GET_DATA:
                    ArrayList<String> result = (ArrayList<String>) msg.obj;
                    data.addAll(result);
                    adapter.notifyDataSetChanged(); // 让listview自动刷新
                    finish = true; // 加载完成
                    if (listview.getFooterViewsCount() > 0) { // 当页脚大于0 就删除页脚
                        listview.removeFooterView(footer);
                    }
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_request);

        listview = (ListView) findViewById(R.id.listview);
        service = new DataService();
        data = new ArrayList<String>();

        List<String> result = service.getData(0, 20);
        data.addAll(result);
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item, R.id.tv_info, data);

        footer = View.inflate(getApplicationContext(), R.layout.footer, null);

        listview.addFooterView(footer); // 这里必须添加页脚让setAdapter里面满足  if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0)条件
        listview.setAdapter(adapter);
        listview.removeFooterView(footer);// 移除掉

        // 设置listview的滚动监听
        listview.setOnScrollListener(new MyOnScrollListener());
    }

    private final class MyOnScrollListener implements OnScrollListener {


        private int countPage = 5; // 加载的总页数
        private int pageSize = 20; // 每页加载20条数据

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // TODO Auto-generated method stub
            Log.i("i", "  scrollState   " + scrollState);
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            // TODO Auto-generated method stub
            Log.i("i", " firstVisibleItem:" + firstVisibleItem + ",visibleItemCount:" + visibleItemCount
                    + ",totalItemCount:" + totalItemCount);

            final int totalCount = firstVisibleItem + visibleItemCount; // 已经加载了多少条目
            int currentPage = totalCount / pageSize; // 当前页
            int nextPage = currentPage + 1; // 下一页
            if (totalCount == totalItemCount && nextPage <= countPage && finish) {// 已经移动到了listview的最后一个条目去加载

                finish = false; // 加载未完成
                // 加载数据时 添加页脚
                listview.addFooterView(footer);

                new Thread() {
                    public void run() {
                        SystemClock.sleep(3000);
                        List<String> result = service.getData(totalCount + 1, pageSize);//
                        // 发送消息给hander
                        Message msg = new Message();
                        msg.what = SUCCESS_GET_DATA;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                }.start();
            }
        }
    }

}
