package com.xiaoming.frameretrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //doRetrofit();
        request();
    }

    //Get请求
    //https://blog.csdn.net/qq_30711091/article/details/80804582
    private void doRetrofit() {
        //总共出现三处url，其中base+Get+传入参数
        //步骤一:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://112.124.22.238:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 步骤二:创建网络请求接口的实例
        ShopService service = retrofit.create(ShopService.class);

        //步骤三：对发送的请求进行封装
        //pageSize=10,curPage=1,传入参数
        Call<ShopBean> callShops = service.getShop(10,1);

        //步骤四:发送网络请求(异步)
        callShops.enqueue(new Callback<ShopBean>() {
            @Override
            public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {
                Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ShopBean> call, Throwable t) {

            }
        });
    }

    //Get请求
    //https://blog.csdn.net/zhiyuan0932/article/details/54318944
    private void request() {
        //总共出现三处url，其中base+Get+传入参数
        //步骤一:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com/") ////指定baseurl
                .addConverterFactory(ScalarsConverterFactory.create()) //设置内容格式,这种对应的数据返回值是String类型
                .client(new OkHttpClient())
                .build();

        // 步骤二:创建网络请求接口的实例,通过retrofit和定义的有网络访问方法的接口关联
        DataService dataService = retrofit.create(DataService.class);

        //步骤三：对发送的请求进行封装
        //在这里又重新设定了一下baidu的地址，是因为Retrofit要求传入具体，如果是决定路径的话，路径会将baseUrl覆盖掉
        Call<String> baidu = dataService.baidu("http://wwww.baidu.com");

        //步骤四:发送网络请求(异步)
        baidu.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MainActivity.this,"code:"+response.code()+"  " + "body:" + response.body(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
