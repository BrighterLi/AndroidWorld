package com.xiaoming.net.download.httpdownload;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.net.R;

//android 文件下载和保存:https://blog.csdn.net/li_yu_csdn/article/details/79313837

//1,在Manifest文件中注册Internet和读写SDCard的权限
//2,下载不能在主线程中进行，要开分线程
public class HttpDownloadActivity extends Activity implements View.OnClickListener {
    Button bt1, bt2;
    private String url = "http://e.hiphotos.baidu.com/image/pic/item/2fdda3cc7cd98d10b510fdea233fb80e7aec9021.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_download);

        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
        bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt1) {
            new downloadFileThread().start();
        } else if (v == bt2) {
            new downloadMP3Thread().start();
        }
    }

    class downloadFileThread extends Thread {
        public void run() {
            HttpDownloader httpDownloader = new HttpDownloader();
            //String fileData=httpDownloader.downloadFiles("http://mystudy.bj.bcebos.com/AndroidDemo_009.xml");
            String fileData = httpDownloader.downloadFiles(url);
            System.out.println("bright8#文件下载结果：" + fileData);
        }
    }

    class downloadMP3Thread extends Thread {
        public void run() {
            HttpDownloader httpDownloader = new HttpDownloader();
            //int downloadResult=httpDownloader.downloadFiles("http://fengkui.bj.bcebos.com/%E8%B6%B3%E9%9F%B3.mp3","BoBoMusic","足音.mp3");
            int downloadResult = httpDownloader.downloadFiles(
                    url, "BoBoMusic", "足音.mp3");
            System.out.println("bright8#mp3下载结果：" + downloadResult);
        }
    }
}
