package com.xiaoming.acrossendwebview.openh5.intercept;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.Toast;

import com.xiaoming.acrossendwebview.MainActivity;
import com.xiaoming.acrossendwebview.R;

import java.io.InputStream;

//shouldInterceptRequest拦截：用代码的html

//WebView拦截url:https://www.jianshu.com/p/55fd544246c2
//其拦截原理是在响应阶段拦截下html数据，然后用本地Html或网络获取的html进行替换，重新加载。
public class ShouldInterceptRequestActivity extends Activity {

    private WebView mWebView;
    private WebViewClientInterceptor mWebViewClientInterceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_should_intercept_request);

        initView();
        initData();
    }

    private void initView() {
        mWebView = findViewById(R.id.web_view);
    }

    private void initData() {
        Log.d("bright8","=========开始加载接收打击========");

        mWebViewClientInterceptor = new WebViewClientInterceptor();
        //webview基础设置
        mWebViewClientInterceptor.setWebViewConfig(mWebView, ShouldInterceptRequestActivity.this);

        mWebViewClientInterceptor.setOnInterceptorListener(new WebViewClientInterceptor.OnInterceptorListener() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.d("bright8","shouldInterceptRequest1#request.getUrl()：" + request.getUrl());
                //用本地Html或网络获取的html进行替换
                String htmlPage = "<html>\n" +
                        "<title>千度</title>\n" +
                        "<body>\n" +
                        "<a href=\"www.taobao.com\">千度</a>,比百度知道的多10倍\n" +
                        "</body>\n" +
                        "<html>";
                InputStream inputStream = mWebViewClientInterceptor.getLocalHtmlPageStream(htmlPage, null);
                WebResourceResponse response = mWebViewClientInterceptor.getWebResourceResponse(inputStream, WebViewClientInterceptor.UTF_8);
                return response;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                String htmlPage = "<html>\n" +
                        "<title>千度</title>\n" +
                        "<body>\n" +
                        "<a href=\"www.taobao.com\">千度</a>,比百度知道的多10倍\n" +
                        "</body>\n" +
                        "<html>";
                InputStream inputStream = mWebViewClientInterceptor.getLocalHtmlPageStream(htmlPage, null);
                WebResourceResponse response = mWebViewClientInterceptor.getWebResourceResponse(inputStream, WebViewClientInterceptor.UTF_8);

                return response;
            }
        });


        //设置WebViewClient向一个网页发送请求，可以返回文本，文件等
        mWebView.setWebViewClient(mWebViewClientInterceptor);
        //设置可让界面弹出alert等提示框
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadUrl("https://www.baidu.com/");
    }


    @Override
    protected void onDestroy() {
        //清理webview相关配置
        mWebViewClientInterceptor.destoryWebViewConfig(mWebView, ShouldInterceptRequestActivity.this);
        super.onDestroy();
    }
}
