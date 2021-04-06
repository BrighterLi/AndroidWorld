package com.xiaoming.frameokhttp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//https://blog.csdn.net/Song74110/article/details/53998052


public class MainActivity extends AppCompatActivity {
    private Button btGet;
    private Button btPost;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btGet = findViewById(R.id.bt_get);
        btPost = findViewById(R.id.bt_post);
        tvShow = findViewById(R.id.tv_show);
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequest();
            }
        });

        btPost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                postRequest();
            }
        });
    }

    //get请求
    private void getRequest() {
        //生成Request对象
        final Request request = new Request.Builder().url("https://www.baidu.com").build();
        //实例化OkHttp
        OkHttpClient client = new OkHttpClient();
        //构造一个Call任务类对象把request对象放进去
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            //失败时候的处理
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //成功时的处理
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                //在主线程中对View的操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvShow.setText(result);
                    }
                });
            }
        });
    }

    //post请求
    private void postRequest() {
        OkHttpClient client = new OkHttpClient();
        //与异步Get请求类似，只是多了用FormBody来封装请求的参数，并传递给Request
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        formBodyBuilder.add("xiaoming", "18520833181");
        Request request = new Request.Builder().url("https://www.baidu.com").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                //在主线程中对View的操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvShow.setText(result);
                    }
                });
            }
        });
    }
}
