package com.fenqile.functionvideorecordingandfacerecognition.vivodetection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fenqile.functionvideorecordingandfacerecognition.R;

//https://github.com/zeusees/Zeusee-Face-Anti-spoofing
public class VivoDetectActivity extends AppCompatActivity {
    private Button mBtnVivoDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivo_detect);

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
