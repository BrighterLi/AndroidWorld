package com.xiaoming.acrossendwebview.webviewandjs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.xiaoming.acrossendwebview.R;

//https://www.cnblogs.com/ssqqhh/p/5657944.html
//JsBridge: https://www.jianshu.com/p/fd29ef3922d8
public class WebViewAndJSActivity extends Activity {
    private static final String TAG = "WebViewAndJSActivity";
    private WebView mWebView;
    private Button btnShow;

    //@SuppressLint标注忽略指定的警告
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_and_js);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        btnShow = findViewById(R.id.btn_show);
        mWebView = findViewById(R.id.webView);
    }

    private void initData() {
        mWebView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = mWebView.getSettings();
        //是否支持 Js 使用,允许JavaScript执行,设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        //设置本地调用对象及其接口(js调用android),添加一个对象, 让javascript可以访问该对象的方法
        mWebView.addJavascriptInterface(new JsInteraction(),"control");
        //加载本地h5页面
        mWebView.loadUrl("file:///android_asset/h5.html");
    }

    private void initEvent() {
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //本地调h5中的方法
                mWebView.loadUrl("javascript:sayHello()");
            }
        });
    }

    /**
     * 第1种：
     * 映射的JS对象名
     * 4.2版本前直接使用该方法存在严重漏洞
     */
    //Android本地方法,js可以调用
    public class JsInteraction {
        //@JavascriptInterface注解来提高安全等级，没有注解的方法，js无法调用
        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
        }
    }
}
