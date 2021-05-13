package com.xiaoming.net.frame.retrofit.get3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.xiaoming.net.R;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//https://blog.csdn.net/u011781521/article/details/71036493?depth_1-utm_source=distribute.pc_relevant.none-task-blog
// -BlogCommendFromBaidu-1&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1
public class Get3Activity extends AppCompatActivity {
    private final String TAG = "Get3Activity";
    private Button btnGet3;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get3);

        initView();
    }

    private void initView() {
        btnGet3 = findViewById(R.id.btn_get3);
        tvShow = findViewById(R.id.tv_show);

        btnGet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();
            }
        });
    }

    //Get请求，异步
    private void get() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://op.juhe.cn/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //接口实体，返回的是String
        RobotServes phoneServes = retrofit.create(RobotServes.class);
        Call<String> call = phoneServes.getString("你好", "2833a660902644508778b5dfd452c080");
        //发送请求
        call.enqueue(new Callback<String>() {
            //响应回调
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse= \n" + "response.message() = " + response.message() + "\n"
                        + "response.body() = " + response.body());
                tvShow.setText("onResponse= \n" + "response.message() = " + response.message() + "\n"
                        + "response.body() = " + response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure = \n" + t.toString());
            }
        });
    }
}
