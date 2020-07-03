package com.xiaoming.functionvideorecordingandfacerecognition.facedetectui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.xiaoming.functionvideorecordingandfacerecognition.R;

public class FaceDetectUiActivity extends Activity {
    private ImageView mIvScanLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_face_detect_ui);

        initView();
        startScanLineAnim();
    }

    private void initView() {
        mIvScanLine = findViewById(R.id.iv_scan_line2);
    }

    private void startScanLineAnim() {
        Animation lineAnim =  new TranslateAnimation(10,10, -500, 400);
        lineAnim.setDuration(1500);
        lineAnim.setRepeatCount(Animation.INFINITE);
        mIvScanLine.setAnimation(lineAnim);
        lineAnim.startNow();
    }
}
