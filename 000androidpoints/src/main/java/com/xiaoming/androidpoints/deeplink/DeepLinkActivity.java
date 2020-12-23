package com.xiaoming.androidpoints.deeplink;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoming.androidpoints.R;

//5分钟搞懂Android DeepLink:https://blog.csdn.net/u012792689/article/details/53925547?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
public class DeepLinkActivity extends Activity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);

        initView();
    }

    //必须先安装分期乐
    private void initView() {
        mWebView = findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/h5.html");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith("fenqile://")) {
                    Uri uri = Uri.parse(url);
                    //Log.i("---------scheme: ", uri.getScheme() + "host: " + uri.getHost() + "Id: " + uri.getPathSegments().get(0));
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                return true;
            }

        });
    }
}
