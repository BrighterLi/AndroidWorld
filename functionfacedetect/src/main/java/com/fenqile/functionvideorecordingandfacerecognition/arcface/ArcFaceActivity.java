package com.fenqile.functionvideorecordingandfacerecognition.arcface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.fenqile.functionvideorecordingandfacerecognition.R;

//官网：https://ai.arcsoft.com.cn/ucenter/resource/build/index.html#/application/139198
//Android人脸识别活体检测开发入门--基于虹软免费SDK实现:https://www.jianshu.com/p/2477790c9d3a
//Android集成虹软人脸、人证对比，活体检测:https://www.jianshu.com/p/8dee89ec4a24
public class ArcFaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_face);
    }
}
