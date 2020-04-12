package com.xiaoming.fuctionchoosephotoandcrop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private Button btnOpenAbulm;
    private Button btnTakePhoto;
    private ImageView imgShow;
    public final int PHOTO_REQUEST_CODE = 1; //相册
    public final int CAMERA_REQUEST_CODE = 2; //相机

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnOpenAbulm = findViewById(R.id.btn_open_album);
        btnTakePhoto = findViewById(R.id.btn_take_photo);
        imgShow = findViewById(R.id.img_show);

        btnOpenAbulm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission()) {
                    openPhotoAlbum();
                }
            }
        });

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission()) {
                    takePhoto();
                }
            }
        });
    }

    //打开相册
    private void openPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);  //选择数据
        intent.setType("image/*"); //选择图片
        startActivityForResult(intent, PHOTO_REQUEST_CODE);
    }

    //拍照
    private void takePhoto() {
        //跳转到系统的拍照界面
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //通过它来调用照相机拍照
        startActivityForResult(takePhotoIntent, CAMERA_REQUEST_CODE);
    }

    // 查看权限并申请
    private boolean checkPermission() {
        //查看是否有权限
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
            //没有权限则申请权限
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST_CODE);
            return  false;
        } else {
            return true;
        }
    }

    //界面回调，用于将得到的照片处理。resultCode判断是确定还是取消
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_CODE:
                //获取相册选中的图片数据Uri
                Uri imageUri = data.getData();
                try {
                    // 将图片Uri转化成Bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    imgShow.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case CAMERA_REQUEST_CODE:
                //获取拍照的图片
                Bundle bundle = data.getExtras();
                Bitmap bitmap = ((Bitmap) bundle.get("data"));
                imgShow.setImageBitmap(bitmap);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
