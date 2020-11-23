package com.xiaoming.net.cache.threelevelcache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

//内存缓存
public class MemoryCache {

    private LruCache<String,Bitmap> mLruCach;

    public MemoryCache(){
        long maxMemory = Runtime.getRuntime().maxMemory()/8;
        mLruCach = new LruCache<String, Bitmap>((int)maxMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return  value.getByteCount();
            }
        };
    }

    public Bitmap getBitmapCacheFromMemory(String url) {
        return  mLruCach.get(url);
    }

    public void setBitmapCacheFromMemory(String url, Bitmap bitmap) {
        mLruCach.put(url,bitmap);
        Log.d("bright8","MemoryCache#save cache to memory");
    }
}
