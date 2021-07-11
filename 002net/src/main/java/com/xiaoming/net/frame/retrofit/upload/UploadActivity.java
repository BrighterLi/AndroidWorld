package com.xiaoming.net.frame.retrofit.upload;

import android.os.Bundle;


import com.xiaoming.net.R;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
    }

    //上传
    //https://www.jianshu.com/p/47b7d7cf3852
    private  void upload(String filePath) throws Exception{
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UploadService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UploadService service = retrofit.create(UploadService.class);
        File file = new File("app/src/ic_launcher.png");
        //以数据量的形式
        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody photo = RequestBody.create(mediaType, file);
        //以文件的形式
        RequestBody description = RequestBody.create(MediaType.parse("combination_to_left_righht/plain"), "description");
        Call<User> updateUser = service.uploadUser(photo, description);
        Response<User> response = updateUser.execute();
        System.out.println("updateUser:" + response.body());

    }
}
