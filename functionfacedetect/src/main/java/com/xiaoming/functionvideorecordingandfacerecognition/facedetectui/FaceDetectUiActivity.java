package com.xiaoming.functionvideorecordingandfacerecognition.facedetectui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaoming.functionvideorecordingandfacerecognition.R;

public class FaceDetectUiActivity extends Activity {
    private ImageView mIvScanLine;
    private FaceMaskView mFaceMaskView;
    private Button mBtTest;
    Animation lineAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_face_detect_ui);

        initView();
        startScanLineAnim();
        stopScanLineAnim();
    }

    private void initView() {
        mIvScanLine = findViewById(R.id.iv_scan_line2);
        mFaceMaskView = findViewById(R.id.face_mask_view);
        mFaceMaskView.setmPaintColor(Color.BLUE);
        mBtTest = findViewById(R.id.bt_test);
        mBtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopScanLineAnim();
            }
        });
    }

    private void startScanLineAnim() {
        lineAnim =  new TranslateAnimation(0,0, -100, 400);
        lineAnim.setDuration(1500);
        lineAnim.setRepeatCount(Animation.INFINITE);
        mIvScanLine.setAnimation(lineAnim);
        lineAnim.startNow();
    }

    private void stopScanLineAnim() {
        mFaceMaskView.setmPaintColor(Color.RED);
        mFaceMaskView.invalidate();
        mIvScanLine.clearAnimation();
        mIvScanLine.setVisibility(View.INVISIBLE);
    }
}
