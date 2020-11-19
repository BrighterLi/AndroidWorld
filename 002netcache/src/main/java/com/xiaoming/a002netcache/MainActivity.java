package com.xiaoming.a002netcache;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.a002netcache.cache.HttpCacheActivity;
import com.xiaoming.a002netcache.cache.mapcache.MapCacheTestActivity;
import com.xiaoming.a002netcache.cache.threelevelcache.ThreeLevelCache;
import com.xiaoming.a002netcache.cache.threelevelcache.ThreeLevelCacheActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.main_list);

        initDemoListData();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoListData);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, HttpCacheActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, MapCacheTestActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ThreeLevelCacheActivity.class));
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
