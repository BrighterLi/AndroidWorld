package com.xiaoming.lifecycle.http;



import com.xiaoming.lifecycle.model.BeautyBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/api/random/data/福利/{count}")
    Observable<BeautyBean> getBeauty(@Path("count") int count);
}
