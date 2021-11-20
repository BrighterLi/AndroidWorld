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
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.widget.R;


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
            cursor.close();
        }
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
