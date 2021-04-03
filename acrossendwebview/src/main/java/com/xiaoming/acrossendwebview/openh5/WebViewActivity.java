package com.xiaoming.acrossendwebview.openh5;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoming.acrossendwebview.R;

import java.io.ByteArrayInputStream;

public class WebViewActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
    }

    private void initView() {
        String url = "http://www.baidu.com";
        webView = (WebView) findViewById(R.id.webview);
        //启用支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        //启用支持DOM Storage
        webView.getSettings().setDomStorageEnabled(true);
        //加载web资源
        webView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("bright8", "shouldOverrideUrlLoading#url:" + url);
                view.loadUrl(url);
                return true;
            }

            //当webview页面有资源请求的时候通知宿主应用，允许应用自己返回数据给webview。
            // 如果返回值是null，就正常加载返回的数据，否则就加载应用自己return的response给webview
            //这个方法回调在子线程而不是UI线程
            //这个方法是用来监控所有的页面请求的，可以用它来监控黑名单以防止页面劫持，
            // 当怀疑域名被劫持时，可以通过本地http请求代理，然后将结果返回给webview
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view , WebResourceRequest request) {
                String result = "<html>\n" +
                        "<title>千度</title>\n" +
                        "<body>\n" +
                        "<a href=\"www.taobao.com\">千度</a>,比百度知道的多10倍\n" +
                        "</body>\n" +
                        "<html>";
                WebResourceResponse response = new WebResourceResponse("text/html",
                        "utf-8",
                        new ByteArrayInputStream(result.getBytes()));
                return response;
            }
        }

        );
    }

    //改写物理按键的返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
