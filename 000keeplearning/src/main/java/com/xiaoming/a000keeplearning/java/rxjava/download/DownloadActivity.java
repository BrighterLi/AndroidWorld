package com.xiaoming.a000keeplearning.java.rxjava.download;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.xiaoming.a000keeplearning.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadActivity extends AppCompatActivity {
    // 打印logcat日志的标签
    private static final String TAG = DownloadActivity.class.getSimpleName();

    // 网络图片的链接地址
    private final static String PATH = "http://pic1.win4000.com/wallpaper/c/53cdd1f7c1f21.jpg";

    // 弹出加载框
    private ProgressDialog progressDialog;

    // ImageView控件，用来显示结果图像
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        image = findViewById(R.id.image);

        // Thread.currentThread().getName(); == Android的主线程
    }
    // 传统方式 思维 无法固定 （后面接手你写的项目，看不懂）
    // A程序员：35356453 自己的思维 不同  封装方法....
    // B程序员：46576576 自己的思维 不同  全部写在一起
    // C程序员：43643654 自己的思维 不同  new Thread
    // D程序员：66545655 自己的思维 不同  使用 线程池
    // todo 目前 Derry老师思维写需求  66545655 思维来写此功能
    // ...
    // 零零散散 麻烦  思维
    public void downloadImageAction(View view) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("下载图片中...");
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(PATH);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    int responseCode = httpURLConnection.getResponseCode(); // 才开始 request
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        Message message = handler.obtainMessage();
                        message.obj = bitmap;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private final Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            image.setImageBitmap(bitmap);

            if (progressDialog != null) progressDialog.dismiss();
            return false;
        }
    });



    // TODO *****************************************************  RxJava　思想思维编程

    /**
     * 封装我们的操作
     * upstream   上游
     * d
     */
    public final static <UD> ObservableTransformer<UD, UD> rxud() {
        return new ObservableTransformer<UD, UD>() {
            @Override
            public ObservableSource<UD> apply(Observable<UD> upstream) {
                return  upstream.subscribeOn(Schedulers.io())     // 给上面代码分配异步线程
                        .observeOn(AndroidSchedulers.mainThread()) // 给下面代码分配主线程;
                        .map(new Function<UD, UD>() {
                            @Override
                            public UD apply(UD ud) throws Exception {
                                Log.d(TAG, "apply: 我监听到你了，居然再执行");
                                return ud;
                            }
                        });
                // .....        ;
            }
        };
    }

    public void rxJavaDownloadImageAction(View view) {
        /*// 起点
        Observable.just(PATH)  // 内部会分发  PATH Stirng  // TODO 第二步

                // TODO 第三步
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        URL url = new URL(PATH);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setConnectTimeout(5000);
                        int responseCode = httpURLConnection.getResponseCode(); // 才开始 request
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            return bitmap;
                        }
                        return null;
                    }
                })
                *//*.map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {
                        Paint paint = new Paint();
                        paint.setTextSize(88);
                        paint.setColor(Color.RED);
                        return drawTextToBitmap(bitmap, "同学们大家好",paint, 88 , 88);
                    }
                })*//*

                // 日志记录
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {
                        Log.d(TAG, "apply: 是这个时候下载了图片啊:" + System.currentTimeMillis());
                        return bitmap;
                    }
                })

                .compose(rxud())

                // 订阅 起点 和 终点 订阅起来
                .subscribe(

                        // 终点
                        new Observer<Bitmap>() {

                            @Override
                            public void onChanged(Bitmap bitmap) {

                            }

                            // 订阅开始
                            @Override
                            public void onSubscribe(Disposable d) {
                                // 预备 开始 要分发
                                // TODO 第一步
                                progressDialog = new ProgressDialog(DownloadActivity.this);
                                progressDialog.setTitle("download run");
                                progressDialog.show();
                            }

                            // TODO 第四步
                            // 拿到事件
                            @Override
                            public void onNext(Bitmap bitmap) {
                                image.setImageBitmap(bitmap);
                            }

                            // 错误事件
                            @Override
                            public void onError(Throwable e) {

                            }

                            // TODO 第五步
                            // 完成事件
                            @Override
                            public void onComplete() {
                                if (progressDialog != null)
                                    progressDialog.dismiss();
                            }
                        });*/

    }


    // 图片上绘制文字 加水印
    private final Bitmap drawTextToBitmap(Bitmap bitmap, String text, Paint paint, int paddingLeft, int paddingTop) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawText(text, paddingLeft, paddingTop, paint);
        return bitmap;
    }

}

