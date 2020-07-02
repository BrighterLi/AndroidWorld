package com.xiaoming.functionvideorecordingandfacerecognition.facedetectui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.xiaoming.functionvideorecordingandfacerecognition.R;

public class FaceDetectUiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_face_detect_ui);
    }
}
