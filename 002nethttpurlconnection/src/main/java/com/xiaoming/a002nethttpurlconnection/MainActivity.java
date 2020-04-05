package com.xiaoming.a002nethttpurlconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button btnGet;
    private Button btnPost;
    private TextView tvShow;
    String resultStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnGet =  findViewById(R.id.btn_get);
        btnPost = findViewById(R.id.btn_post);
        tvShow = findViewById(R.id.tv_show);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        resultStr = httpGet("http://www.baidu.com");
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvShow.setText(resultStr);
                            }
                        });
                    }
                }).start();

            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //Get请求
    public String httpGet(String urlPath) {
        HttpURLConnection connection = null;
        InputStream  inputStream = null;
        try {
            //获得URL对象
            URL url = new URL(urlPath);
            //获得HttpURLConnection对象
            connection = ((HttpURLConnection) url.openConnection());
            //默认为GET
            connection.setRequestMethod("GET");
            //不使用缓存
            connection.setUseCaches(false);
            //设置超时时间
            connection.setConnectTimeout(10000);
            //设置读取超时时间
            connection.setReadTimeout(10000);
            //设置是否从httpUrlConnection读入，默认情况下是true
            connection.setDoInput(true);
            //相应码是否为200
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //获得输入流
                inputStream = connection.getInputStream();
                //包装字节流为字符流
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
                connection = null;
            }
            if(inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
