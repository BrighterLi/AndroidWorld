package com.xiaoming.a002netretrofit.asynget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import com.xiaoming.a002netretrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Get请求
//总共出现三处url，其中base+Get+传入参数
//https://blog.csdn.net/qq_30711091/article/details/80804582
public class GetActivity extends AppCompatActivity {
    private Button button;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        tvShow = findViewById(R.id.tv_show);
        button = findViewById(R.id.btn_get_net);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestNet();
            }
        });
    }

   void requestNet(){
        //Retrofit对象
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("http://112.124.22.238:8081/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();

       //ShopService
       ShopService service = retrofit.create(ShopService.class);

       //Call对象
       //pageSize = 10, curPage = 1
       Call<ShopBean> callShops = service.getShop(10,1);

       //网络请求，异步
       callShops.enqueue(new Callback<ShopBean>() {
           @Override
           public void onResponse(Call<ShopBean> call, final Response<ShopBean> response) {
               Log.e("response",response.toString());
               Toast.makeText(GetActivity.this, "response.code():" + response.code(), Toast.LENGTH_SHORT).show();
               runOnUiThread(
                       new Thread(new Runnable() {
                           @Override
                           public void run() {
                               tvShow.setText(response.toString());
                           }
                       })
               );
           }

           @Override
           public void onFailure(Call<ShopBean> call, Throwable t) {
               Log.e("failure","fail");
           }
       });
    }
}
