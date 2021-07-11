package com.xiaoming.net.frame.retrofit.post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

//这是一个接口类. LoginBean则是数据返回后的Bean类(Retrofit会自动使用导入的Gson解析)
public interface HttpList {

    @FormUrlEncoded //注解表示from表单  还有@Multipart 表单可供使用 当然你也可以不添加
    @POST("combination_to_left_righht/login_test") //网络请求路径,这路径最前面不能加斜杠 /  ,否则它会自动裁剪路径,这样会导致你的路径错误
    Call<LoginBean> login(@Field("number") String number, @Field("password") String password); //@Field("number") 为post值的的key
}
