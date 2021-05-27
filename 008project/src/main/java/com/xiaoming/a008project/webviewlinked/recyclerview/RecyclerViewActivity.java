package com.xiaoming.a008project.webviewlinked.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.xiaoming.a008project.R;
import com.xiaoming.a008project.webviewlinked.DpUtil;

//RecyclerView里面套用webview 显示不全: https://blog.csdn.net/u013200864/article/details/51766931
public class RecyclerViewActivity extends AppCompatActivity {
    LinearLayout mRootView;
    WebView1 mWebView;
    RecyclerView rv;
    String url = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRootView = findViewById(R.id.root_view);
        rv = (RecyclerView) findViewById(R.id.rv);
        RecyclerViewActivity.MyAdapter adapter = new RecyclerViewActivity.MyAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(llm);

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == 2){
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                WebView1 mView = new WebView1(parent.getContext());
                mView.setLayoutParams(lp);
                return new MyViewHolder(mView);
            }else{
                ViewGroup.LayoutParams viewLp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DpUtil.dip2px(RecyclerViewActivity.this, 300));
                View  view = new View(parent.getContext());
                view.setLayoutParams(viewLp);
                view.setBackgroundColor(0xFFF74C31);
                return new MyViewHolder(view);
            }

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
            if(position == 0){
                return 1;
            }else{
                return 2;
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            WebView1 mView;
            public MyViewHolder(View itemView) {
                super(itemView);
                if(itemView instanceof WebView1){
                    initWebView(itemView);
                }
            }

            String html = " <p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524899133986.jpg\" style=\"\" title=\"1462524899133986.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524905929684.jpg\" style=\"\" title=\"1462524905929684.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524910520453.jpg\" style=\"\" title=\"1462524910520453.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524914347104.jpg\" style=\"\" title=\"1462524914347104.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524927778143.jpg\" style=\"\" title=\"1462524927778143.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462535930458058.jpg\" title=\"1462535930458058.jpg\" alt=\"806337621792081796.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462535960619691.jpg\" style=\"\" title=\"1462535960619691.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462535965617029.jpg\" style=\"\" title=\"1462535965617029.jpg\"/></p><p><br/></p><p><br/></p><p><br/></p>\n";

            private void initWebView(View itemView) {
                mWebView = (WebView1) itemView;
                //WebSettings mSetting = mWebView.getSettings();
                //mSetting.setJavaScriptEnabled(true);
//                mWebView.setWebChromeClient(new WebChromeClient());
//                mWebView.setWebViewClient(new WebViewClient(){
//                });
                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        return super.shouldOverrideUrlLoading(view, request);
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {

                    }

                    @Override
                    public void onLoadResource(WebView view, String url) {
                    }

                    @Override
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        super.onReceivedError(view, errorCode, description, failingUrl);
                    }

                    @Override
                    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                        super.onReceivedSslError(view, handler, error);
                        handler.proceed();
                    }
                });

                mWebView.setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                    }

                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                    }
                });

                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mWebView != null) {
                            // WebView设置固定高度，避免各种嵌套问题
                            ViewGroup.LayoutParams lp = mWebView.getLayoutParams();
                            lp.height = mRootView.getHeight();
                            mWebView.setLayoutParams(lp);
                        }
                    }
                });

                //mWebView.loadData(html,"text/html","utf-8");
                mWebView.loadUrl(url);
            }
        }
    }
}
