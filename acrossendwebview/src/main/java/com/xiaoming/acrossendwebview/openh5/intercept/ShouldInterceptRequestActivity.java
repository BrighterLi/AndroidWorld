package com.xiaoming.acrossendwebview.openh5.intercept;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.Toast;

import com.xiaoming.acrossendwebview.MainActivity;
import com.xiaoming.acrossendwebview.R;

//WebView拦截url:https://www.jianshu.com/p/55fd544246c2
public class ShouldInterceptRequestActivity extends Activity {

    private WebView mWebView;
    private WebViewClientInterceptor mWebViewClientInterceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_should_intercept_request);

        initView();
        initData();
        setListener();
    }

    private void initView() {
        mWebView = findViewById(R.id.web_view);
    }

    private void initData() {
        mWebViewClientInterceptor = new WebViewClientInterceptor();
        //webview基础设置
        mWebViewClientInterceptor.setWebViewConfig(mWebView, ShouldOverrideUrlLoadingActivity.this);


        mWebViewClientInterceptor.setOnOverrideUrlListener(new WebViewClientInterceptor.OnOverrideUrlListener() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
                Toast.makeText(ShouldInterceptRequestActivity.this, "这是拦截url的操作,原url="+webView.getUrl(), Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                Toast.makeText(ShouldInterceptRequestActivity.this, "这是拦截url的操作,原url="+webView.getUrl(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

        //设置WebViewClient向一个网页发送请求，可以返回文本，文件等
        mWebView.setWebViewClient(mWebViewClientInterceptor);
        //设置可让界面弹出alert等提示框
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadUrl("file:///android_asset/test.html");
    }

    private void setListener() {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        //清理webview相关配置
        mWebViewClientInterceptor.destoryWebViewConfig(mWebView, MainActivity.this);
        super.onDestroy();
    }
}
