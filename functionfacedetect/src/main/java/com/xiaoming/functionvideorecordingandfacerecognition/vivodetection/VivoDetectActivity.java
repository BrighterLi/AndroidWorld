package com.xiaoming.functionvideorecordingandfacerecognition.vivodetection;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xiaoming.functionvideorecordingandfacerecognition.R;

import pub.devrel.easypermissions.EasyPermissions;

//https://github.com/zeusees/Zeusee-Face-Anti-spoofing
public class VivoDetectActivity extends AppCompatActivity {
    private Button mBtnVivoDetect;
    private int RC_READ_EXTERNAL_STORAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivo_detect);

        if (!EasyPermissions.hasPermissions(
                VivoDetectActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        )
                ) {

            EasyPermissions.requestPermissions(
                    this,
                    "申请人脸检测相关权限",
                    RC_READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO //音频录制
            );
        }

        initView();


    }

    private void initView() {
        mBtnVivoDetect = findViewById(R.id.btn_vivo_detect);

        mBtnVivoDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VivoDetectActivity.this, DetectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
