package com.xiaoming.acrossendwebview.openh5.intercept;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.xiaoming.acrossendwebview.MainActivity;
import com.xiaoming.acrossendwebview.R;

import java.io.InputStream;

//WebView拦截url:https://www.jianshu.com/p/55fd544246c2
//其拦截原理是在响应阶段拦截下html数据，然后用本地Html或网络获取的html进行替换，重新加载。
//网络Html替换加载
public class ShouldInterceptRequest2Activity extends Activity {

    private WebView mWebView;
    private WebViewClientInterceptor mWebViewClientInterceptor;
    private String mTempResult;
    private long mLastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_should_intercept_request2);

        initView();
        initData();
    }

    public String getmTempResult() {
        return mTempResult;
    }

    public void setmTempResult(String mTempResult) {
        this.mTempResult = mTempResult;
    }

    private void initView() {
        mWebView = findViewById(R.id.web_view);
    }

    private void initData() {
        mWebViewClientInterceptor = new WebViewClientInterceptor();
        //webview基础设置
        mWebViewClientInterceptor.setWebViewConfig(mWebView, ShouldInterceptRequest2Activity.this);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                Log.i("bright8", "doInBackground");
                return mWebViewClientInterceptor.getDataByUrl("https://github.com/");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("bright8", "onPostExecute#s:" + s);
                setmTempResult(s);
            }
        }.execute();

        mWebViewClientInterceptor.setOnInterceptorListener(new WebViewClientInterceptor.OnInterceptorListener() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.i("bright8", "shouldInterceptRequest#request.getUrl()：" + request.getUrl());
                mLastTime = System.currentTimeMillis();
                while (true) {
                    Log.i("bright8", "shouldInterceptRequest============等待=========");
                    if (getmTempResult() != null || (System.currentTimeMillis() - mLastTime) > 1000 * 10) {
                        break;
                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (getmTempResult() != null) {
                    String temp = getmTempResult();
                    if (temp.contains("utf-8")) {
                        temp = temp.replaceAll("utf-8", "");
                    }

                    InputStream inputStream = mWebViewClientInterceptor.getLocalHtmlPageStream(temp, null);
                    WebResourceResponse response = mWebViewClientInterceptor.getWebResourceResponse(inputStream, WebViewClientInterceptor.UTF_8);
                    return response;
                }
                return null;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                mLastTime = System.currentTimeMillis();
                Log.i("bright8", "shouldInterceptRequest2" );
                while (true) {
                    Log.i("bright8", "shouldInterceptRequest2============等待=========");
                    if (getmTempResult() != null || (System.currentTimeMillis() - mLastTime) > 1000 * 10) {
                        break;
                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (getmTempResult() != null) {
                    String temp = getmTempResult();
                    if (temp.contains("utf-8")) {
                        temp = temp.replaceAll("utf-8", "");
                    }

                    InputStream inputStream = mWebViewClientInterceptor.getLocalHtmlPageStream(temp, null);
                    WebResourceResponse response = mWebViewClientInterceptor.getWebResourceResponse(inputStream, WebViewClientInterceptor.UTF_8);
                    return response;
                }
                return null;
            }
        });

        //设置WebViewClient向一个网页发送请求，可以返回文本，文件等
        mWebView.setWebViewClient(mWebViewClientInterceptor);
        //设置可让界面弹出alert等提示框
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("https://www.baidu.com/");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        //清理webview相关配置
        mWebViewClientInterceptor.destoryWebViewConfig(mWebView, ShouldInterceptRequest2Activity.this);
        super.onDestroy();
    }
}
