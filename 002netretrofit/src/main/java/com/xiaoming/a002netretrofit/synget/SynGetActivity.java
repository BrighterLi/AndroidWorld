package com.xiaoming.a002netretrofit.synget;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoming.a002netretrofit.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

//Get同步请求
//https://www.jianshu.com/p/5eb51d134995
public class SynGetActivity extends AppCompatActivity implements View.OnClickListener{
    private Button synGetBtn;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syn_get);
        initView();

    }

    private void initView() {
        tvShow = findViewById(R.id.tv_show);
        synGetBtn = findViewById(R.id.btn_syn_get);

        synGetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_syn_get:
                getExecute();
                break;
        }
    }

    //Get同步请求
    private void getExecute() {
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.WEAL_URL)
                .build();
        //2.获取ApiService接口服务对象
        ApiService apiService = retrofit.create(ApiService.class);
        //3.获取Call对象
        final Call<ResponseBody> call = apiService.getWealData();
        new Thread(new Runnable() { //子线程执行
            @Override
            public void run() {
                //4.Call对象执行请求
                try {
                    final Response<ResponseBody> response = call.execute(); //同步请求
                    final String result = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Response.raw,获取来自服务器的原始套接字响应
                            Toast.makeText(SynGetActivity.this, "response.raw():"+response.raw(), Toast.LENGTH_SHORT).show();
                            tvShow.setText(result);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
