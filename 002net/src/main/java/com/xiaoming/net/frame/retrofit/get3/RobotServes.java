package com.xiaoming.net.frame.retrofit.get3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RobotServes {
    //使用这个接口返回的值是String
    @GET("robot/index")
    Call<String> getString(@Query("info") String info, @Query("key") String key);

    //使用这个接口返回的值已经封装成实体类了
    @GET("robot/index")
    Call<RobotResponse> get(@Query("info") String info, @Query("key") String key);
}
