package com.widget.pullrefresh;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.widget.R;

import java.util.ArrayList;
import java.util.List;

public class PullRefreshActivity extends AppCompatActivity {
    private List<String> list;
    private MyListView lv;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh);

        lv = findViewById(R.id.listview);

        list = new ArrayList<String>();
        list.add("log");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");
        list.add("hello world");

        adapter = new ListViewAdapter(list, this);
        lv.setAdapter(adapter);

        lv.setonRefreshListener(new MyListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //new MyTask().execute();
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        list.add("刷新后添加的内容");
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                       adapter.notifyDataSetChanged();
                       lv.onRefreshComplete();
                    }
                }.execute(null, null, null);
            }
        });
    }

    class  MyTask extends AsyncTask{
       /* @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }*/

        @Override
        protected void onPostExecute(Object o) {
            adapter.notifyDataSetChanged();
            lv.onRefreshComplete();
            super.onPostExecute(o);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //list.add("刷新后添加的内容");
            return null;
        }
    }
}


