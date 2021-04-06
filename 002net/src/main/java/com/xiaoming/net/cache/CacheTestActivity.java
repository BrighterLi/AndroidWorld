package com.xiaoming.net.cache;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.net.R;
import com.xiaoming.net.cache.mapcache.MapCacheTestActivity;
import com.xiaoming.net.cache.threelevelcache.ThreeLevelCacheActivity;

import java.util.ArrayList;
import java.util.List;

public class CacheTestActivity extends Activity {
    private ListView listView;
    private List<String> demoListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_test);

        listView = findViewById(R.id.main_list);

        initDemoListData();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoListData);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(CacheTestActivity.this, HttpCacheActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(CacheTestActivity.this, MapCacheTestActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(CacheTestActivity.this, ThreeLevelCacheActivity.class));
                        break;
                }
            }
        });

    }

    private void  initDemoListData() {
        demoListData = new ArrayList<>();
        demoListData.add("Http下载图片进行缓存");
        demoListData.add("Map内存进行缓存");
        demoListData.add("三级缓存");
    }

}

