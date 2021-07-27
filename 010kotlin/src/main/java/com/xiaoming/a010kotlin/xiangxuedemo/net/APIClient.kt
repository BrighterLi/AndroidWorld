package com.xiaoming.a010kotlin.xiangxuedemo.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * 访问服务器的API接口(WanAndroidAPI) 的 客户端管理
 */
class APIClient {
    private object Holder {
        val INSTANCE = APIClient()
    }

    //派生
    companion object {
        // WanAndroidAPI实例化这个，  XXXAPI实例化这个，   BBBAPI实例化
        val instance = Holder.INSTANCE
    }

    fun <T> instanceRetrofit(apiInterface: Class<T>) : T {
        // OKHttpClient请求服务器
        val mOKHttpClient = OkHttpClient().newBuilder()
                // 添加读取超时时间
                .readTimeout(1000, TimeUnit.SECONDS)
                // 添加连接超时时间
                .connectTimeout(10000,TimeUnit.SECONDS)
                // 添加写出超时时间
                .writeTimeout(10000, TimeUnit.SECONDS)
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com")
                //请求方
                .client(mOKHttpClient)
                //响应方
                //Response的事情 回来
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava来处理
                .build()

                return retrofit.create(apiInterface)

    }
}