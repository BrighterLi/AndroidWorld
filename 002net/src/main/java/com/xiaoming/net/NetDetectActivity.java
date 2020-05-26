package com.xiaoming.net;

import android.content.Context;
import android.icu.text.MessagePattern;
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
import java.net.MalformedURLException;
import java.net.URL;


//网络检测
public class NetDetectActivity extends AppCompatActivity {
    private Button mBtnNetDetect;
    private TextView mTvDnsAddress;

    private String[] platforms = {
            "http://pv.sohu.com/cityjson",
            "http://pv.sohu.com/cityjson?ie=utf-8",
            "http://ip.chinaz.com/getip.aspx"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_detect);

        initView();
    }

    private void initView() {
        mBtnNetDetect = findViewById(R.id.bt_net_detect);
        mTvDnsAddress = findViewById(R.id.tv_dns_address);

        mBtnNetDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dnsAddress = getDnsAddress();
                mTvDnsAddress.setText("dns地址：" + dnsAddress);
            }
        });
    }

    //https://www.cnblogs.com/alex-zhao/p/5254624.html
    private String getDnsAddress() {
        Process cmdProcess = null;
        BufferedReader reader = null;
        String dnsAddress = "";
        try {
            cmdProcess = Runtime.getRuntime().exec("getprop net.dns1");
            reader = new BufferedReader(new InputStreamReader(cmdProcess.getInputStream()));
            dnsAddress = reader.readLine();
            return dnsAddress;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cmdProcess.destroy();
        }
    }

    //公网出口IP
    private String getOutNetIp(Context context, int index) {
        if (index < platforms.length) {
            BufferedReader reader = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = null;
                url = new URL(platforms[index]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(5000);
                urlConnection.setConnectTimeout(5000);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = urlConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    //while ((line = reader.readLine()))
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //UID
    //操作系统

    //ping
    //
}
