package com.xiaoming.net;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.net.cache.CacheTestActivity;
import com.xiaoming.net.download.DownloadTestActivity;
import com.xiaoming.net.frame.FrameTestActivity;
import com.xiaoming.net.netdetect.NetDetectActivity;
import com.xiaoming.net.packegenet.PackegeNetDetectActivity;
import com.xiaoming.net.pingnet.PingNetActivity;
import com.xiaoming.net.pingnet2.PingNetActivity2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mLvMain;
    private List<String> mDemoDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoData();
        initView();
    }

    private void initDemoData() {
        mDemoDataList.add("网络检测");
        mDemoDataList.add("网络检测封装");
        mDemoDataList.add("ping网络");
        mDemoDataList.add("ping网络2");
        mDemoDataList.add("缓存");
        mDemoDataList.add("下载");
        mDemoDataList.add("网络框架");
    }

    private void initView() {
        mLvMain = findViewById(R.id.lv_main);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDemoDataList);
        mLvMain.setAdapter(arrayAdapter);

        mLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, NetDetectActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, PackegeNetDetectActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, PingNetActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, PingNetActivity2.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, CacheTestActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, DownloadTestActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, FrameTestActivity.class));
                        break;
                }
            }
        });
    }
}
