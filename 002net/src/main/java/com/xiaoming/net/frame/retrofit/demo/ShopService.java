package com.xiaoming.net.frame.retrofit.demo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//传入的参数
//其中Query 是作为url的参数的，使用时类似“user/john?password=xxxx”这样，
// 而Path是用来替换你路径里的条目的，类似“user/{username}”
public interface ShopService {
@GET("/course_api/wares/hot")
Call<ShopBean> getShop(@Query("pageSize") int pageSize, @Query("curPage") int curPage);
}
