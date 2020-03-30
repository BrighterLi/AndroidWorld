package com.xiaoming.a002netretrofit.get;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopService {
    @GET("/course_api/wares/hot")
    Call<ShopBean> getShop(@Query("pageSize")int pageSize,@Query("curPage") int curPage);
}
