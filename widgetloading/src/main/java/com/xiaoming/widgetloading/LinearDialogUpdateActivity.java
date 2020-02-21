package com.xiaoming.widgetloading;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LinearDialogUpdateActivity extends AppCompatActivity {
    private Button btOpen;
    private ProgressDialog progressDialog;
    private int currentProgress;
    private int add;
    private final static int MAX_VALUE = 100;

    //在主线程里执行UI的状态的变化。这个Handler是在主线程创建的，默认绑定的主线程
    Handler handler = new Handler() {
        @Override   //这里可以去掉。？
        public void handleMessage(Message msg) {
            if(msg.what == 123) {
                //设置进度条的进度
                progressDialog.setProgress(currentProgress);
            }
            if(currentProgress >= MAX_VALUE) {
                //让进度条消失
                progressDialog.dismiss();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_dialog_update);

        btOpen = findViewById(R.id.bt_linear_dialog_update_on);
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //初始化属性
                currentProgress = 0;
                add = 0;
                //依次设置一些属性
                progressDialog = new ProgressDialog(LinearDialogUpdateActivity.this);
                progressDialog.setMax(MAX_VALUE);
                progressDialog.setTitle("文件读取中");
                progressDialog.setMessage("文件加载中，请稍后...");
                //这里设置为不可以通过按取消按钮关闭进度条
                progressDialog.setCancelable(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //圆形
                //这里设置的是是否显示进度,设为false才是显示
                progressDialog.setIndeterminate(false);
                progressDialog.show();

                //新建一个线程,重写run()方法
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        while (currentProgress < MAX_VALUE) {  //未达到最大值一直进行
                            //这里的算法是决定进度条变化的,可以按需要写
                            currentProgress = 2 * useTime();
                            //把信息码发送给handle让更新界面
                            handler.sendEmptyMessage(123);
                        }
                    }
                }.start();

            }
        });
    }

    //设置一个耗时的方法
    private int useTime() {
        add++;
        try {
            //睡100ms
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return add;
    }
}
