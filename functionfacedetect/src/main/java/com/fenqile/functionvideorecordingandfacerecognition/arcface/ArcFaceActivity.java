package com.fenqile.functionvideorecordingandfacerecognition.arcface;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.LivenessInfo;
import com.fenqile.functionvideorecordingandfacerecognition.R;
import com.fenqile.functionvideorecordingandfacerecognition.arcface.utils.face.FaceListener;

//官网：https://ai.arcsoft.com.cn/ucenter/resource/build/index.html#/application/139198
//Android人脸识别活体检测开发入门--基于虹软免费SDK实现:https://www.jianshu.com/p/2477790c9d3a
//Android集成虹软人脸、人证对比，活体检测:https://www.jianshu.com/p/8dee89ec4a24
public class ArcFaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_face);

        final FaceListener faceListener = new FaceListener() {
            @Override
            public void onFail(Exception e) {

            }

            @Override
            public void onFaceFeatureInfoGet(@Nullable FaceFeature faceFeature, Integer requestId, Integer errorCode) {

            }

            @Override
            public void onFaceLivenessInfoGet(@Nullable LivenessInfo livenessInfo, Integer requestId, Integer errorCode) {

            }
        };
    }
}
