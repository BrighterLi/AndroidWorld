package com.xiaoming.acrossendwebview.openh5.intercept;

import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.webkit.CookieManager;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

//WebViewClient
public class WebViewClientInterceptor extends WebViewClient {
    public static final String UTF_8 = "UTF-8";
    public static final String GBK = "GBK";

    private OnOverrideUrlListener mOnOverrideUrlListener;
    private OnInterceptorListener mOnInterceptorListener;

    //设置url加载的监听
    public void setOnOverrideUrlListener(OnOverrideUrlListener listener) {
        this.mOnOverrideUrlListener = listener;
    }

    //设置响应实体的监听
    public void setOnInterceptorListener(OnInterceptorListener listener) {
        this.mOnInterceptorListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
        //返回true表示拦截url加载，false表示默认加载url
        /*if (mOnOverrideUrlListener != null) {
            return mOnOverrideUrlListener.shouldOverrideUrlLoading(webView, request);
        }*/
        return super.shouldOverrideUrlLoading(webView, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Log.d("WebViewClient", "bright8#shouldOverrideUrlLoading#url: " + url);
        Log.d("WebViewClient", "bright8#shouldOverrideUrlLoading#webView.getUrl(): " +  webView.getUrl());
        //返回true表示拦截url加载，false表示默认加载url
        if (mOnOverrideUrlListener != null) {
            return mOnOverrideUrlListener.shouldOverrideUrlLoading(webView, url);
        }
        return super.shouldOverrideUrlLoading(webView, url);
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest request) {
        //Log.d("WebViewClient", "bright8#shouldInterceptRequest#request.getUrl(): " + request.getUrl());
        //Log.d("WebViewClient", "bright8#shouldInterceptRequest#webView.getUrl(): " +  webView.getUrl());

        //返回null表示加载默认请求，否则加载拦截后改变的实体
        if (mOnInterceptorListener != null) {
            return mOnInterceptorListener.shouldInterceptRequest(webView, request);
        }
        return super.shouldInterceptRequest(webView, request);
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, String url) {
        //返回null表示加载默认请求，否则加载拦截后改变的实体
        if (mOnInterceptorListener != null) {
            return mOnInterceptorListener.shouldInterceptRequest(webView, url);
        }
        return super.shouldInterceptRequest(webView, url);
    }

    //拦截url加载的监听
    public interface OnOverrideUrlListener {
        //boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request);

        boolean shouldOverrideUrlLoading(WebView webView, String url);
    }

    //拦截相应实体的监听
    public interface OnInterceptorListener {
        WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request);

        WebResourceResponse shouldInterceptRequest(WebView view, String url);
    }

    public WebResourceResponse getWebResourceResponse(InputStream data, String charset) {
        return new WebResourceResponse("combination_to_left_righht/javascript", charset, data);
    }

    /*
     * 将html写的界面转成io流
     * @param htmlPage html代码
     * @param charset WebViewClientInterceptor.UTF_8或WebViewClientInterceptor.GBK为null时表示使用默认编码集
     *
     * @return
     * */
    public InputStream getLocalHtmlPageStream(String htmlPage, String charset) {
//        String htmlPage = "<html>\n" +
//                "<title>千度</title>\n" +
//                "<body>\n" +
//                "<a href=\"www.taobao.com\">千度</a>,比百度知道的多10倍\n" +
//                "</body>\n" +
//                "<html>";
        InputStream stream = null;
        if (!TextUtils.isEmpty(charset)) {
            stream = new ByteArrayInputStream(htmlPage.getBytes(Charset.forName(charset)));
        } else {
            stream = new ByteArrayInputStream(htmlPage.getBytes());
        }
        return stream;
    }

    public String getDataByUrl(String url) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            URL tempUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) tempUrl.openConnection();
            //连接主机超时时间
            httpURLConnection.setConnectTimeout(10 * 1000);
            //设置从主机读取数据超时
            httpURLConnection.setReadTimeout(40 * 1000);
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    //WebView基本设置
    public void setWebViewConfig(WebView webView, Context context) {
        if (webView == null) {
            return;
        }
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(false); //不支持缩放
        webSettings.setJavaScriptEnabled(true); //设置WebView应许执行JavaScript脚本
        //自适应屏幕大小
        webSettings.setUseWideViewPort(true); //当前页面包含viewport属性标签，在标签中指定宽度值生效
        webSettings.setLoadWithOverviewMode(true); //设置WebView使用预览模式加载界面
        String cacheDirPath = context.getFilesDir().getAbsolutePath() + "cache/";
        webSettings.setAppCachePath(cacheDirPath);
        webSettings.setAppCacheEnabled(true); //设置Application缓存API开启
        webSettings.setDomStorageEnabled(true); //设置开启DOM存储API权限，WebView能够使用DOM storage API
        webSettings.setAllowFileAccess(true); //设置在WebView内部应许访问文件
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //设置缓存加载模式
        webSettings.setTextZoom(100); //设置WebView中加载页面字体变焦百分比，默认100
        webSettings.setSupportMultipleWindows(true); //设置webview支持多屏窗口
        //webview支持https和http混合加载模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
    }

    //关闭界面清理webview配置
    public void destoryWebViewConfig(WebView webView, Context context){
        if(webView != null) {
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.getSettings().setJavaScriptEnabled(false);
            webView.clearCache(true);
        }
        if(context != null) {
            //清空缓存，解决webview加载界面不全的问题
            CookieSyncManager.createInstance(context);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();

    }

}
