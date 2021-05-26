package com.xiaoming.a008project.webviewlinked.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.xiaoming.a008project.R;

//RecyclerView里面套用webview 显示不全: https://blog.csdn.net/u013200864/article/details/51766931
public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView rv;
    String url = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        rv = (RecyclerView) findViewById(R.id.rv);
        RecyclerViewActivity.MyAdapter adapter = new RecyclerViewActivity.MyAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(llm);

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if(viewType == 1 ){
                WebView mView = new WebView(parent.getContext());
                mView.setLayoutParams(lp);
                return new MyViewHolder(mView);
            }else{
                TextView mView = new TextView(parent.getContext());
                mView.setLayoutParams(lp);
                mView.setText("aaaaaaaa");
                return new MyViewHolder(mView);
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
            return 66;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            WebView mView;
            public MyViewHolder(View itemView) {
                super(itemView);
                if(itemView instanceof WebView){
                    initWebView(itemView);
                }
            }

            String html = " <p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524899133986.jpg\" style=\"\" title=\"1462524899133986.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524905929684.jpg\" style=\"\" title=\"1462524905929684.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524910520453.jpg\" style=\"\" title=\"1462524910520453.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524914347104.jpg\" style=\"\" title=\"1462524914347104.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462524927778143.jpg\" style=\"\" title=\"1462524927778143.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462535930458058.jpg\" title=\"1462535930458058.jpg\" alt=\"806337621792081796.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462535960619691.jpg\" style=\"\" title=\"1462535960619691.jpg\"/></p><p><img src=\"http://test.hitheone.com/uploads/ueditor/image/20160506/1462535965617029.jpg\" style=\"\" title=\"1462535965617029.jpg\"/></p><p><br/></p><p><br/></p><p><br/></p>\n";

            private void initWebView(View itemView) {
                WebView mWebView = (WebView) itemView;
                WebSettings mSetting = mWebView.getSettings();
                mSetting.setJavaScriptEnabled(true);
//                mWebView.setWebChromeClient(new WebChromeClient());
//                mWebView.setWebViewClient(new WebViewClient(){
//                });

                mWebView.loadData(html,"text/html","utf-8");
                //mWebView.loadUrl(url);
            }
        }
    }
}
