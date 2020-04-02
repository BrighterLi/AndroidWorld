package com.xiaoming.a002netretrofit.post;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoming.a002netretrofit.R;

import java.io.IOException;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//demo报404错误，应该是该链接资源失效了？？？
//Post请求，同步，异步
//https://www.cnblogs.com/guanxinjing/p/11594249.html
public class PostActivity extends AppCompatActivity {
    private static final String TAG = "PostActivity";
    private Retrofit mRetrofit;
    private Button btnAsyncPost;
    private Button btnSyncPost;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        initHttpBase();
        initView();

    }

    private void initView() {
        btnAsyncPost = findViewById(R.id.btn_asyn_post);
        btnSyncPost = findViewById(R.id.btn_syn_post);
        tvShow = findViewById(R.id.tv_show);

        btnAsyncPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAsyncHttp();
            }
        });

        btnSyncPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postSyncHttp();
            }
        });
    }

    private void initHttpBase() {
        mRetrofit = new Retrofit.Builder()
                //base的网络地址  baseUrl不能为空,且强制要求必需以 / 斜杠结尾
                .baseUrl("http://doclever.cn:8090/mock/5c3c6da33dce46264b24452b/")
                //使用Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                //使用单独的线程处理 (这很重要,一般网络请求如果不设置可能不会报错,但是如果是下载文件就会报错)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();
    }

    //异步请求
    private void postAsyncHttp() {
        HttpList httpList = mRetrofit.create(HttpList.class);
        Call<LoginBean> call = httpList.login("181234123", "123456");
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                LoginBean bean = response.body();
                Log.e(TAG,"onResponse:code=" + bean.getCode());
                Log.e(TAG,"onResponse:message=" + bean.getMessage());
                //tvShow.setText(bean.toString());
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.e(TAG,"onFailure:网络请求失败=" + t.getMessage());
            }
        });
    }

    //同步请求
    private void postSyncHttp() {
        HttpList httpList = mRetrofit.create(HttpList.class);
        final Call<LoginBean> call = httpList.login("181234123", "123456");
        //Android主线程不能操作网络请求,所以new一个线程来操作
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Response<LoginBean> response = call.execute(); //同步请求网络
                    LoginBean bean = response.body();
                    Log.e(TAG, "onResponse: code=" + bean.getCode());
                    Log.e(TAG, "onResponse: message=" + bean.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
