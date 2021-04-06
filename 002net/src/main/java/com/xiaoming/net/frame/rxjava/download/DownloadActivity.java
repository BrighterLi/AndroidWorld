package com.xiaoming.net.frame.rxjava.download;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.net.R;

public class DownloadActivity extends Activity implements View.OnClickListener{
    private Button mBtImgDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mBtImgDownload = findViewById(R.id.bt_img_download);
        mBtImgDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_img_download:
                DownloadUtil.downLoadImage(null);

        }
    }
}
