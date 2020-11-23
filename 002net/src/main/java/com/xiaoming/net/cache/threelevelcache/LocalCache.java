package com.xiaoming.net.cache.threelevelcache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//本地缓存
public class LocalCache {

    private Context mContext;
    public LocalCache(Context ctx){
        mContext = ctx;
    }

    //从本地获取缓存
    public Bitmap getBitmapCacheFromLocal(String url) {
        String name = null;
        try {
            name = MD5Encoder.encode(url);
            File file = new File(getCachePath(),name);
            if(file.exists()) {
                //return BitmapFactory.decodeStream(new FileInputStream(file));
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //?异常
    //java.io.FileNotFoundException: /storage/emulated/0/LocalCache/com.song.a3gcacheutils/data/bb1e11b4462dd5a5a44dca03f045edde: open failed: ENOENT (No such file or directory)
    public void setBitmapCacheFromLocal(String url, Bitmap bitmap) {

        String name ;
        FileOutputStream fos = null;

        try {
            name = MD5Encoder.encode(url);
            File file = new File(getCachePath(),name);
            File parentFile = file.getParentFile();
            if(!parentFile.exists())
                parentFile.mkdirs();
            //save bitmap to local file
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            Log.d("bright8","LocalCache#save cache to local");
        } catch (Exception e) {
            Log.d("bright8","LocalCache#e:" + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if(fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * get cache path
     * @return path
     */
    private String getCachePath(){
        String state = Environment.getExternalStorageState();
        String path = "LocalCache/"+mContext.getPackageName()+"/data";
        File dir;
        if(!TextUtils.isEmpty(state) && Environment.MEDIA_MOUNTED.equals(state)){
            //has sdCard
            dir = new File(Environment.getExternalStorageDirectory(),path);
        } else {
            //no scCard
            dir = new File(mContext.getCacheDir(),path);
        }
        return dir.getAbsolutePath();
    }

}
