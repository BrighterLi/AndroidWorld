package com.xiaoming.a000keeplearning.java.rxjava.retrofit_okhttp_rxjava.retrofit_okhttp;

import io.reactivex.Observable;
import retrofit2.http.Body;

// 请求接口 API
public interface IReqeustNetwor {

    // 请求注册 功能  todo 耗时操作 ---> OkHttp
    public Observable<RegisterResponse> registerAction(@Body RegisterRequest registerRequest);

    // 请求登录 功能 todo 耗时操作 ---> OKHttp
    public Observable<LoginResponse> loginAction(@Body LoginReqeust loginRequest);

}
