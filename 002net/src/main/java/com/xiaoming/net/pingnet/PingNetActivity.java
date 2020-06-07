package com.xiaoming.net.pingnet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xiaoming.net.R;

import java.net.InetAddress;
import java.net.UnknownHostException;

//https://blog.csdn.net/li13650639161/article/details/78465850?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-4
public class PingNetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_net);

        //网络操作应在子线程中操作，避免阻塞UI线程，导致ANR
        new Thread(new Runnable() {
            @Override
            public void run() {
                PingNetEntity pingNetEntity=new PingNetEntity("www.baidu.com",3,5,new StringBuffer());
                pingNetEntity=PingNetUtil.ping(pingNetEntity);
                Log.i("bright#testPing",pingNetEntity.getIp());
                Log.i("bright#testPing","time="+ pingNetEntity.getPingTime());
                Log.i("bright#testPing","result=" + pingNetEntity.isResult()+"");
                Log.i("bright#testPing","parsedIp=" + pingNetEntity.getParsedIp()+"");
            }
        }).start();
    }
}
