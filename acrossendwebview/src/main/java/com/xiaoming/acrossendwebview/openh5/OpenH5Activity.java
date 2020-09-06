package com.xiaoming.acrossendwebview.openh5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Button;

import com.xiaoming.acrossendwebview.R;

//打开H5页面
public class OpenH5Activity extends Activity {
    private static final String URL = "http://www.baidu.com";
    private EditText et_address;
    private WebView myWebView;
    private Button mBtLoadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_open_h5);

        et_address = findViewById(R.id.et_address);
        myWebView = findViewById(R.id.myWebView);
        mBtLoadUrl = findViewById(R.id.bt_load_url);

        WebSettings mySettings = myWebView.getSettings();
        mySettings.setSupportZoom(true);
        mySettings.setBuiltInZoomControls(true);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("bright8","shouldOverrideUrlLoading#url:" + url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        mBtLoadUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUrlPingduoduo();
            }
        });
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

    private void loadUrlPingduoduo() {
        String url = "https://mobile.yangkeduo.com/app.html?use_reload=1&launch_url=duo_coupon_landing.html%3Fgoods_id%3D133002461085%26pid%3D10320141_139864283%26cpsSign%3DCC_200817_10320141_139864283_27a9689746404f915b1c98f2c20cc05d%26duoduo_type%3D2&campaign=ddjb&cid=launch_dl_force_";
        String url2 =  "pinduoduo://com.xunmeng.pinduoduo/duo_coupon_landing.html?goods_id=133002461085&pid=10320141_139864283&cpsSign=CC_200817_10320141_139864283_27a9689746404f915b1c98f2c20cc05d&duoduo_type=2&_p_launch_id=10784_1605014712130_8ec2kgjbsc";
        myWebView.loadUrl(url);
    }

    //启动android默认浏览器
    //如果手机本身安装了多个浏览器而又没有设置默认浏览器的话，系统将让用户选择使用哪个浏览器来打开连接
    private void openUrl() {
        Uri uri = Uri.parse("https://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    //启动指定浏览器打开
    private void openUrl2() {
        Uri uri = Uri.parse("https://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        //intent.setClassName("com.UCMobile","com.uc.browser.InnerUCMobile");//打开UC浏览器
        intent.setClassName("com.tencent.mtt","com.tencent.mtt.MainActivity");//打开QQ浏览器
        startActivity(intent);

    }

}
