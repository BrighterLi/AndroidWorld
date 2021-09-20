package com.xiaoming.net.frame.rxjava.rxjava_retrofit.demo;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

import android.os.Bundle;

import com.google.gson.Gson;
import com.xiaoming.net.R;

import java.util.HashMap;
import java.util.Observable;

//Android RxJava系列三: 与Retrofit2结合使用和封装处理:https://segmentfault.com/a/1190000019683544
public class RxjavaRetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_retrofit);


    }

    private void request() {
        GetRequest request = new Retrofit.Builder()
                .baseUrl("http://www.kuaidi100.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GetRequest.class);

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "bright");
        Gson gson = new Gson();
        String strEntity = gson.toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),strEntity);


    }
}
