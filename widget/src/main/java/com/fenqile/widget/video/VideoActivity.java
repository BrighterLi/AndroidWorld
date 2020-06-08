package com.fenqile.widget.video;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.fenqile.widget.R;


public class VideoActivity extends AppCompatActivity {
    private VideoView mVideoView;
    private Button mBtStart, mBtStop;
    private MediaController mMdController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        initView();
    }

    private void initView() {
        mVideoView = findViewById(R.id.video_view);
        mBtStart = findViewById(R.id.bt_start);
        mBtStop = findViewById(R.id.bt_pause);

        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });

        mBtStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVideoView.stopPlayback();
            }
        });
    }

    private void init() {
        mVideoView = findViewById(R.id.video_view);
        mMdController = new MediaController(this);
        //视频资源存放在res资源包raw文件夹下面
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.test;
        mVideoView.setVideoURI(Uri.parse(uri));
        mVideoView.setMediaController(mMdController);
        mMdController.setMediaPlayer(mVideoView);
        mVideoView.requestFocus();
        mVideoView.start();
    }
}
