package com.xiaoming.net.frame.rxjava.rxjava_retrofit.demo;


import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface GetRequest {

    @POST("/app/auth/school/list")
    Observable<School> postSchool(@Body RequestBody route);//根据学校名获取学校
}