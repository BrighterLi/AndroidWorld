package com.widget.video.choosevideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hw.videoprocessor.VideoProcessor;
import com.widget.R;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Calendar;


public class ChooseActivity extends AppCompatActivity {
    private Button mBtChooseVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        mBtChooseVideo = findViewById(R.id.mBtChooseVideo);
        mBtChooseVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionBeforeChooseVideo();
            }
        });
    }

    private void checkPermissionBeforeChooseVideo() {
        if(checkReadAndWriteStoragePermission()) {
            Log.i("ChooseActivity", "bright8#checkPermissionBeforeChooseVideo#has permission");
            chooseVideo();
        } else {
            Log.i("ChooseActivity", "bright8#checkPermissionBeforeChooseVideo#request permission");
            ActivityCompat.requestPermissions(ChooseActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},111 );
        }

    }

    private void chooseVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedVideo = data.getData();
            String[] filePathColumn = {MediaStore.Video.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String videoPath = cursor.getString(columnIndex);
            //long videoSize = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
            //AppLog.i(TAG, "onActivityResult#videoPath：" + videoPath + " #videoSize" + videoSize / 1024 / 1024);
            Toast.makeText(this, videoPath, Toast.LENGTH_LONG).show();
            Log.i("ChooseActivity", "chooseActivity#videoPath: " + videoPath);
            compressVideo(videoPath);
            cursor.close();
        }
    }

    //VideoProcessor
    //https://github.com/yellowcath/VideoProcessor
    private void compressVideo(String inputVideoPath) {
        String outputPath = getOutputPath();
        try {
            VideoProcessor.processor(this)
                    .input(inputVideoPath)
                    .output(outputPath)
                    .process();
        } catch (Exception e) {
            Log.i("ChooseActivity", "chooseActivity#compressVideo#e: " + e);
            e.printStackTrace();
        }
    }

    private String getOutputPath() {
        File mVideoFile;
        String outputPath;
        outputPath = getSDPath();
        if (outputPath != null) {
            //创建文件
            mVideoFile = new File(outputPath + "/recordvideo");
            if (!mVideoFile.exists()) {
                mVideoFile.mkdir();
            }
            outputPath = mVideoFile + "/" + getData() + ".mp4"; // mPath: /storage/emulated/0/recordvideo/
            //把视频放到本地sd card的mPath路径的文件
            return outputPath;
        }
        return null;

    }

    /**
     * 获取SD path
     */
    public String getSDPath() {
        File sdDir = null;
        //获取sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); //sdcard
        if (sdCardExist) {
            //获取根目录
            sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }
        return null;
    }

    /**
     * 获取系统时间
     */
    public static String getData() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR);
        int second = calendar.get(Calendar.SECOND);

        return "" + year + (month + 1) + day + hour + minute + second;

    }

    //SiliCompressor
    // Android视频压缩并且上传   https://www.cnblogs.com/wzqnxd/p/10038881.html
    private void compressVideo2(final String inputVideoPath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /*try {
                    String compressPath = SiliCompressor.with(this).compressVideo(inputVideoPath, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }*/
            }
        }).start();
    }

    //VideoCompressor
    //https://github.com/fishwjy/VideoCompressor
    private void compressVideo3(final String inputVideoPath) {
        /*VideoCompressTask task = VideoCompress.compressVideoLow(inputVideoPath, destPath, new VideoCompress.CompressListener() {
            @Override
            public void onStart() {
                //Start Compress
            }

            @Override
            public void onSuccess() {
                //Finish successfully
            }

            @Override
            public void onFail() {
                //Failed
            }

            @Override
            public void onProgress(float percent) {
                //Progress
            }
        });*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean checkReadAndWriteStoragePermission() {
        PackageManager pm = getPackageManager();
        boolean readStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean writeStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if(readStoragePermission && writeStoragePermission) {
            return true;
        }
        return false;
    }
}
