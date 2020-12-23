package com.xiaoming.androidpoints.deeplink;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.xiaoming.androidpoints.R;

import java.util.List;

public class ToDeepLinkActivity extends Activity {
    private TextView mTextView;
    String scheme;
    String host;
    List<String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_deep_link);

        initView();
        getDataFromBrowser();
    }

    private void initView() {
        mTextView = findViewById(R.id.tv_show);
    }

    /**
     * 从deep link中获取数据
     */
    private void getDataFromBrowser() {
        Uri data = getIntent().getData();
        try {
            scheme = data.getScheme(); // "will"
            host = data.getHost(); // "share"
            params = data.getPathSegments();
            String testId = params.get(0); // "uuid"
            mTextView.setText("Scheme: " + scheme + "\n" + "host: " + host + "\n" + "params: " + testId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
