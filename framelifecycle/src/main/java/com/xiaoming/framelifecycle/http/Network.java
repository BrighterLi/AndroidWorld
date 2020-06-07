package com.xiaoming.framelifecycle.http;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public static final String BEAUTY = "http://gank.io/";
    public static ApiService getApiService() {
        return getRetrofit(BEAUTY).build().create(ApiService.class);
    }

    private static Retrofit.Builder getRetrofit(String baseUrl) {
        return new Retrofit.Builder().client(new OkHttpClient().newBuilder().build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
    }
}
