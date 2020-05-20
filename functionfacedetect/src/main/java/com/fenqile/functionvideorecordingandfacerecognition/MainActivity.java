package com.fenqile.functionvideorecordingandfacerecognition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fenqile.functionvideorecordingandfacerecognition.videorecord.RecordActivity;
import com.fenqile.functionvideorecordingandfacerecognition.vivodetection.VivoDetectActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnRecordVideo;
    private Button mBtnVivoDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtnRecordVideo = findViewById(R.id.btn_record_video);
        mBtnVivoDetect = findViewById(R.id.btn_vivo_detect);

        mBtnRecordVideo.setOnClickListener(this);
        mBtnVivoDetect.setOnClickListener(this);
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
        }
    }
}
