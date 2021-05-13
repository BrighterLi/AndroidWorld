package com.xiaoming.net.frame.retrofit.upload;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

//Part和PartMap
//Part和PartMap与注解Multipart配合使用，用于文件的上传，如果是单文件上传使用Part即可，如果是多文件上传使用PartMap；
// 作用于参数上，并且在运行时解析。

//单文件上传
public interface UploadService {
    static final  String BASE_URL = "";
    @Multipart
    @POST("user/photo")
    Call<User> uploadUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);
}
