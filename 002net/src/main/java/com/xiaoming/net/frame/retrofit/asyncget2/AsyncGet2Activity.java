package com.xiaoming.net.frame.retrofit.asyncget2;

import android.os.Bundle;
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

//Get异步请求
//https://blog.csdn.net/qq_23077365/article/details/52164375
public class AsyncGet2Activity extends AppCompatActivity {
    private Button asyncGet2Btn;
    private TextView showTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_get2);

        initView();
    }

    private void initView() {
        showTv = findViewById(R.id.tv_show);
        asyncGet2Btn = findViewById(R.id.btn_asyn_get2);

        asyncGet2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncGet2();
            }
        });
    }

    private void asyncGet2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://112.124.22.238:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ShopService shopService = retrofit.create(ShopService.class);
        Call<ShopBean2> callShops = shopService.getShop2(10,1);

        callShops.enqueue(new Callback<ShopBean2>() {
            @Override
            public void onResponse(Call<ShopBean2> call, Response<ShopBean2> response) {
                if(response.isSuccessful()) {
                    final ShopBean2 result = response.body();
                    runOnUiThread(new Thread(new Runnable() {
                        @Override
                        public void run() {
                            showTv.setText(result.toString());
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<ShopBean2> call, Throwable t) {

            }
        });
    }
}
