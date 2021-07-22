package com.widget.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.widget.R;

import java.io.File;

public class CameraActivity extends AppCompatActivity {
    private Button mOpenCameraBt;
    private Button mOpenAlbumBt;

    private String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setImgFilePath();
        initView();
    }

    private void initView() {
        mOpenCameraBt = findViewById(R.id.open_camera);
        mOpenAlbumBt = findViewById(R.id.open_album);

        mOpenCameraBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        mOpenAlbumBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });

    }

    //拍照
    private void openCamera() {
        CameraPhotoUtils.openCamera(this, mFilePath, 1, 1001);
    }

    public void setImgFilePath() {
        File cacheDir = getExternalCacheDir();
        if (cacheDir == null) {
            cacheDir = getCacheDir();
        }
        //构建临时文件路径
        mFilePath = cacheDir.getAbsolutePath() + File.separator + "bright" +
                File.separator + "photo" + File.separator + System.currentTimeMillis() + ".jpg";
        Log.i("CameraActivity", "bright8#mFilePath: " + mFilePath);
        File tempFile = new File(mFilePath);
        File parentFile = tempFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    //相册
    private void openAlbum() {
        CameraPhotoUtils.openAlbum(this, 2, 1002);
    }
}
