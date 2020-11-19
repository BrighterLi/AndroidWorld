package com.xiaoming.a002netcache.cache.threelevelcache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


//从网络下载图片
public class NetworkCatche {

    private MemoryCache mMemoryCache;
    private LocalCache mLocache;

    public NetworkCatche(MemoryCache memoryCache, LocalCache localCache) {
        mMemoryCache = memoryCache;
        mLocache = localCache;
    }

    public void getBitmapFromNet(ImageView iv, String url) {
        new BitmapTast().execute(iv,url);
    }

    class BitmapTast extends AsyncTask<Object,Void,Bitmap> {

        private ImageView iv;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... objects) {
            iv = (ImageView) objects[0];
            url = (String) objects[1];
            return downLoadBitmap(url);
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(null != bitmap){
                iv.setImageBitmap(bitmap);
                Log.d("bright8","NetworkCatche#first get data form net");

                //add cache to Local
                mLocache.setBitmapCacheFromLocal(url,bitmap);
                //add cache to memory
                mMemoryCache.setBitmapCacheFromMemory(url,bitmap);
            }
        }
    }

    //图片下载
    private Bitmap downLoadBitmap(String url) {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5*1000);
            conn.setReadTimeout(5*1000);
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if(200 == responseCode){//200 == request  respon ok
                //压缩图片
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;//取样压缩原来的1/2
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                return BitmapFactory.decodeStream(conn.getInputStream(),null,options);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return null;
    }

}
