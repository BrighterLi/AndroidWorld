package com.xiaoming.a002netretrofit.post2;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServer {
    String NEWS_URL = "http://api.shujuzhihui.cn/api/news/";

    //新闻类别列表
    @POST("categories")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<ResponseBody> getCategories(@Field("appKey") String appKey);

    //新闻列表
    @POST("list?appKey=be3ac46843914f5cbe875defccd8f392")
    @Headers("Content-Type:application/json")
    Call<ResponseBody> getList(@Body RequestBody requestBody);

    //新闻详情
    @POST("detail")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<ResponseBody> getDetail(@Field("appKey") String appKey, @Field("newsId") String newsId);

}
