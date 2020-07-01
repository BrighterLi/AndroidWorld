package com.xiaoming.functionvideorecordingandfacerecognition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.functionvideorecordingandfacerecognition.circularring.CircularRingActivity;
import com.xiaoming.functionvideorecordingandfacerecognition.transparent.TransparentActivity;
import com.xiaoming.functionvideorecordingandfacerecognition.videorecord.RecordActivity;
import com.xiaoming.functionvideorecordingandfacerecognition.vivodetection.VivoDetectActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnRecordVideo;
    private Button mBtnVivoDetect;
    private Button mBtTransparentActivity;
    private Button mBtCircularRing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtnRecordVideo = findViewById(R.id.btn_record_video);
        mBtnVivoDetect = findViewById(R.id.btn_vivo_detect);
        mBtTransparentActivity = findViewById(R.id.btn_transparent_activity);
        mBtCircularRing = findViewById(R.id.btn_circular_ring);

        mBtnRecordVideo.setOnClickListener(this);
        mBtnVivoDetect.setOnClickListener(this);
        mBtTransparentActivity.setOnClickListener(this);
        mBtCircularRing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_record_video:
                startActivity(new Intent(MainActivity.this, RecordActivity.class));
                break;
            case R.id.btn_vivo_detect:
                startActivity(new Intent(MainActivity.this, VivoDetectActivity.class));
                break;
            case R.id.btn_transparent_activity:
                startActivity(new Intent(MainActivity.this, TransparentActivity.class));
                break;
            case R.id.btn_circular_ring:
                startActivity(new Intent(MainActivity.this, CircularRingActivity.class));
                break;
        }
    }
}
