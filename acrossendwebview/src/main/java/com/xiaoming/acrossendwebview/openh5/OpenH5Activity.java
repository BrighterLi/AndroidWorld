package com.xiaoming.acrossendwebview.openh5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.xiaoming.acrossendwebview.R;

//打开H5页面
public class OpenH5Activity extends Activity {
    private static final String URL = "http://www.baidu.com";
    private EditText et_address;
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_open_h5);

        et_address = findViewById(R.id.et_address);
        myWebView = findViewById(R.id.myWebView);

        WebSettings mySettings = myWebView.getSettings();
        mySettings.setSupportZoom(true);
        mySettings.setBuiltInZoomControls(true);
        myWebView.setWebViewClient(new WebViewClient());
    }

    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.addSubMenu(0,0,0,"刷新");
        menu.addSubMenu(0,0,1,"前进");
        menu.addSubMenu(0,0,2,"后退");
        return true;
    }

    //菜单
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getOrder()) {
            case 0:
                //网页加载
                myWebView.reload();
                break;
            case 1:
                if(myWebView.canGoBack()) {
                    //网页后退
                    myWebView.goBack();
                } else {}
                break;
            case 2:
                //网页
                if(myWebView.canGoForward()) {
                    myWebView.goForward();
                }
                break;
        }
        return true;
    }

    public void click(View v) {
        //获取ExitView的内容，并去掉内容的空格
        String url = et_address.getText().toString().trim();
        if(url == null || url.isEmpty()) {
            url = URL;
        }
        //打开h5网页
        myWebView.loadUrl(url);
    }

}
