package com.xiaoming.a002netretrofit.asyncget2;

import com.xiaoming.a002netretrofit.asynget.ShopBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopService {
    @GET("/course_api/wares/hot")
    Call<ShopBean2> getShop2(@Query("pageSize") int pageSize, @Query("curPage") int curPage);
}
