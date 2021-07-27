package com.xiaoming.a010kotlin.xiangxuedemo.api

import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginResponse
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginResponseWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*


// 客户端API 可以访问 服务器的API
interface WanAndroidAPI {
    /**
     * 登录API
     * username=Derry-vip&password=123456
     */

    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(@Field("username") username: String,
                    @Field("password") password: String)
    : Observable<LoginResponseWrapper<LoginResponse>> //返回值

}