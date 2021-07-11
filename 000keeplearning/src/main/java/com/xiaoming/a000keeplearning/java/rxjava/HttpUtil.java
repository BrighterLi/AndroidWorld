package com.xiaoming.a000keeplearning.java.rxjava;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

    private static final String TAG = "HttpUtils";

    /**
     * 默认 combination_to_left_righht-a环境
     */
    public static String BASE_URL = "https://www.wanandroid.com/";

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    /**
     * 根据各种配置创建出Retrofit
     *
     * @return 返回创建好的Retrofit
     */
    public static Retrofit getOnlineCookieRetrofit() {
        // OKHttp客户端
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        // 各种参数配置
        OkHttpClient okHttpClient = httpBuilder
                .addNetworkInterceptor(new StethoInterceptor())
                .readTimeout(10000, TimeUnit.SECONDS)
                .connectTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .build();


        return new Retrofit.Builder().baseUrl(BASE_URL)
                // TODO 请求用 OKhttp
                .client(okHttpClient)

                // TODO 响应RxJava
                // 添加一个json解析的工具
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                // 添加rxjava处理工具
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build();
    }
}
