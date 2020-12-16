package com.xiaoming.net.frame.rxjava.download;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


public class DownloadUtil {


    //Rxjava+HttpURLConnection
    //Rxjava+HttpURLConnection下载图片
    public static void downLoadImage(View view) {
        //Observable是被观察者；大家记住OnSubscribe<Bitmap>里面的bitmap;
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Log.d("bright8", "DownloadUtil#downLoadImage#Observable.create#call");
                Bitmap bitmap = getBitMap();
                //onNext的方法传入的参数；
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.d("bright8", "DownloadUtil#downLoadImage#doOnSubscribe#call");
                        //progressBar.setVisibility(View.VISIBLE);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
                        Log.d("bright8", "DownloadUtil#downLoadImage#subscribe#onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("bright8", "DownloadUtil#downLoadImage#subscribe#onError");
                    }
                    //得到onNext里面的参数
                    @Override
                    public void onNext(Bitmap bitmap) {
                        Log.d("bright8", "DownloadUtil#downLoadImage#subscribe#onNext");
                        //progressBar.setVisibility(View.GONE);
                        //bitmapImage.setImageBitmap(bitmap);
                    }
                });
    }

    public static Bitmap getBitMap() {
        //String imagePath = "http://images2015.cnblogs.com/blog/841455/201512/841455-20151201235212327-794268895.jpg";
        String imagePath = "http://e.hiphotos.baidu.com/image/pic/item/2fdda3cc7cd98d10b510fdea233fb80e7aec9021.jpg";
        try {
            URL url = new URL(imagePath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int responseCode = conn.getResponseCode();
            Log.d("bright8", "DownloadUtil#getBitMap#responseCode:" + responseCode);
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Log.d("bright8", "DownloadUtil#getBitMap#bitmap: " + bitmap);
                return bitmap;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
