package com.xiaoming.net.download;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.net.R;
import com.xiaoming.net.download.httpdownload.HttpDownloadActivity;

public class DownloadTestActivity extends Activity {
    private Button mBtHttpDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_test);

        initView();
        initEvent();
    }

    private void initView() {
        mBtHttpDownload = findViewById(R.id.bt_http_download);
    }

    private void initEvent() {
        mBtHttpDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DownloadTestActivity.this, HttpDownloadActivity.class));
            }
        });
    }
}
