package com.xiaoming.acrossendwebview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.acrossendwebview.longclicktosavepic.WebViewActivity2;
import com.xiaoming.acrossendwebview.openh5.AutomaticallyJumpActivity;
import com.xiaoming.acrossendwebview.openh5.OpenH5Activity;
import com.xiaoming.acrossendwebview.openh5.ShowHtmlActivity;
import com.xiaoming.acrossendwebview.openh5.WebViewActivity;
import com.xiaoming.acrossendwebview.openh5.intercept.ShouldInterceptRequest2Activity;
import com.xiaoming.acrossendwebview.openh5.intercept.ShouldInterceptRequestActivity;
import com.xiaoming.acrossendwebview.openh5.intercept.ShouldOverrideUrlLoadingActivity;
import com.xiaoming.acrossendwebview.viewpagerswiperconflict.ViewpagerSwiperConflitActivity;
import com.xiaoming.acrossendwebview.webviewandjs.WebViewAndJSActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDemoList();

        //创建适配器
        //这里ListView的适配器选用ArrayAdapter，ListView中每一项的布局选用系统的simple_list_item_1
        listView = findViewById(R.id.lv_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, demoNameList);

        //绑定适配器
        listView.setAdapter(adapter);

        // 通过一个实现OnItemClickListener接口的匿名类的onItemClick方法来处理ListView中每一项的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                      startActivity(new Intent(MainActivity.this, OpenH5Activity.class));
                      break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, WebViewAndJSActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ShowHtmlActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ShouldOverrideUrlLoadingActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, ShouldInterceptRequestActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, ShouldInterceptRequest2Activity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, AutomaticallyJumpActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, WebViewActivity2.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, ViewpagerSwiperConflitActivity.class));
                        break;
                }
            }
        });
    }

    private void initDemoList() {
        demoNameList.add("打开H5页面");
        demoNameList.add("WebView");
        demoNameList.add("Android与js交互");
        demoNameList.add("通过WebView获取解析html内容");
        demoNameList.add("拦截之shouldOverrideUrlLoading");
        demoNameList.add("拦截之shouldInterceptRequest：用代码的html");
        demoNameList.add("拦截之shouldInterceptRequest：用网络的html");
        demoNameList.add("打开的html页面自动跳转到指定的网址");
        demoNameList.add("长按h5页面的图片保存图片");
        demoNameList.add("安卓viewpager嵌套webview的滑动冲突解决");
    }
}
