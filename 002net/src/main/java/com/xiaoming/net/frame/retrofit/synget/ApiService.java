package com.xiaoming.net.frame.retrofit.synget;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String WEAL_URL = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";
    @GET("10/1")
    Call<ResponseBody> getWealData();

}
