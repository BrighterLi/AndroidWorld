package com.fenqile.functionvideorecordingandfacerecognition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fenqile.functionvideorecordingandfacerecognition.videorecord.RecordActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnRecordVideo;
    private Button mBtnFaceRecognition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtnRecordVideo = findViewById(R.id.btn_record_video);
        mBtnFaceRecognition = findViewById(R.id.btn_face_recognition);

        mBtnRecordVideo.setOnClickListener(this);
        mBtnFaceRecognition.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_record_video:
                startActivity(new Intent(MainActivity.this, RecordActivity.class));
                break;
        }
    }
}
