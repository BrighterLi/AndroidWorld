package com.xiaoming.a002netretrofit.post2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaoming.a002netretrofit.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

//报502 – 网关错误？？？
//post同步请求
//https://www.jianshu.com/p/73ebc8036dc3
public class Post2Activity extends AppCompatActivity implements View.OnClickListener{
    private Button bt_categories;
    private Button bt_list;
    private Button bt_detail;
    private TextView tv;
    private RecyclerView rlv;
    private Retrofit mRetrofit;
    private ApiServer mApiServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);

        initView();

        // 如果出现Socket 超时，可以设置如下代码：增大客户端的超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", String
                .valueOf(10000));// （单位：毫秒）
        System.setProperty("sun.net.client.defaultReadTimeout", String
                .valueOf(10000)); // （单位：毫秒）

    }

    private void initView() {
        bt_categories = findViewById(R.id.bt_categories);
        bt_list = findViewById(R.id.bt_list);
        bt_detail = findViewById(R.id.bt_detail);
        tv = findViewById(R.id.tv);
        rlv = findViewById(R.id.rlv);

        bt_categories.setOnClickListener(this);
        bt_list.setOnClickListener(this);
        bt_detail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_categories:
                // 1.获取Retrofit对象
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(ApiServer.NEWS_URL)
                        .build();
                // 2.获取到ApiServer接口服务对象
                mApiServer = mRetrofit.create(ApiServer.class);
                // 3.获取Call对象
                final Call<ResponseBody> categories = mApiServer.getCategories("be3ac46843914f5cbe875defccd8f392");

                new Thread(new Runnable() { //子线程执行
                    @Override
                    public void run() {
                        try {
                            // 4. call对象执行同步请求，请求数据在子线程中执行
                            Response<ResponseBody> execute = categories.execute();
                            final String result = execute.body().string();

                            runOnUiThread(new Runnable() { //主线程执行
                                @Override
                                public void run() {
                                    if(result != null) {
                                        tv.setText(result); //设置数据时，要在主线程中运行
                                    }
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
            case R.id.bt_list:
                // 1.获取Retrofit对象
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(ApiServer.NEWS_URL)
                        .build();
                // 2.获取到ApiServer接口服务对象
                mApiServer = mRetrofit.create(ApiServer.class);

                // 获取到请求类型,MediaType指的是要传递的数据的MIME类型
                MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");

                // 获取到请求内容
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("category","要闻");
                    jsonObject.put("page", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String str = jsonObject.toString();

                // 获取到请求体(RequestBody)
                final RequestBody requestBody = RequestBody.create(mediaType, str);

                // 3.获取Call对象
                final Call<ResponseBody> list = mApiServer.getList(requestBody);

                new Thread(new Runnable() { //子线程执行
                    @Override
                    public void run() {
                        try {
                            // 4. call对象执行同步请求，请求数据在子线程中执行
                            Response<ResponseBody> execute = list.execute();
                            final String result = execute.body().string();

                            runOnUiThread(new Runnable() { //主线程执行
                                @Override
                                public void run() {
                                    if(result != null) {
                                        tv.setText(result); //设置数据时，要在主线程中运行
                                    }
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
            case R.id.bt_detail:
                // 1.获取Retrofit对象
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(ApiServer.NEWS_URL)
                        .build();
                // 2.获取到ApiServer接口服务对象
                mApiServer = mRetrofit.create(ApiServer.class);
                // 3.获取Call对象
                final Call<ResponseBody> detail = mApiServer.getDetail("be3ac46843914f5cbe875defccd8f392", "82264991a5932770734bbd386aebedf6");

                new Thread(new Runnable() { //子线程执行
                    @Override
                    public void run() {
                        try {
                            // 4. call对象执行同步请求，请求数据在子线程中执行
                            Response<ResponseBody> execute = detail.execute();
                            final String result = execute.body().string();

                            runOnUiThread(new Runnable() { //主线程执行
                                @Override
                                public void run() {
                                    if(result != null) {
                                        tv.setText(result); //设置数据时，要在主线程中运行
                                    }
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
        }
    }
}
