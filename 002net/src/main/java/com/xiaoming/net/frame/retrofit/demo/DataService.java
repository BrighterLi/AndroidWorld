package com.xiaoming.net.frame.retrofit.demo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DataService {
    @GET
    Call<String> baidu(@Url String url);
}
