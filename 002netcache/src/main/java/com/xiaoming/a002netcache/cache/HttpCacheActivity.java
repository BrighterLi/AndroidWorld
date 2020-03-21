package com.xiaoming.a002netcache.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaoming.a002netcache.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// 网络数据的缓存HttpResponseCache
// https://blog.csdn.net/qq_31726827/article/details/50557377
public class HttpCacheActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "HttpCacheActivity";
    private Button mButton;
    private Button mReButton;
    private ImageView imageView;

    private HttpHandler mHttpHandler;
    private Bitmap bitmap;

    private DownInformationFromNet work = new DownInformationFromNet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_cache);

        // 设置缓存的路径
        File httpCacheDir = new File(HttpCacheActivity.this.getCacheDir(),"http");
        // 设置缓存的大小
        long httpCacheSize = 100 * 1024 * 1024;
        try {
            HttpResponseCache.install(httpCacheDir,httpCacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mButton = findViewById(R.id.button);
        mReButton = findViewById(R.id.reload);
        imageView = findViewById(R.id.image);

        mButton.setOnClickListener(this);
        mReButton.setOnClickListener(this);

        mHttpHandler = new HttpHandler(this);

        bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button:
                work.execute();
                break;
            case R.id.reload:
                //加载图片生成bitmap
                bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
                imageView.setImageBitmap(bitmap);
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        HttpResponseCache cache = HttpResponseCache.getInstalled();
        if(cache != null) {
            cache.flush();
        }
    }

    //主线程里显示图片
    private class HttpHandler extends Handler{
        private HttpCacheActivity mHttpCacheActivity;
        public HttpHandler(Context context) {
            mHttpCacheActivity = (HttpCacheActivity)context;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mHttpCacheActivity.imageView.setImageBitmap(bitmap);
        }
    }

    //异步线程加载图片
    private class DownInformationFromNet extends AsyncTask<Integer,String, String> {

        @Override
        protected String doInBackground(Integer... integers) {
            try {
                //URL url = new URL("http://pic31.nipic.com/20130724/5252423_104848296000_2.jpg");
                URL url = new URL("https://cimg1.fenqile.com/ibanner2/M00/33/12/jqgHAFxKhA-AbVJPAAAJlDpoEak013.png");

                try {
                    //连接
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.connect();

                    if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        //获取网络资源
                        InputStream inputStream = connection.getInputStream();
                        ////加载图片生成bitmap
                        bitmap = BitmapFactory.decodeStream(inputStream);
                    }
                    connection.disconnect();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            mHttpHandler.sendEmptyMessage(0);
        }
    }
}
