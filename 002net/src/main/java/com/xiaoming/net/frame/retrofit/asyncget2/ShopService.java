package com.xiaoming.net.frame.retrofit.asyncget2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopService {
    @GET("/course_api/wares/hot")
    Call<ShopBean2> getShop2(@Query("pageSize") int pageSize, @Query("curPage") int curPage);
}
