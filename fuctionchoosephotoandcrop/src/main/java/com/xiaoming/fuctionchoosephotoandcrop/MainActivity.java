package com.xiaoming.fuctionchoosephotoandcrop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ChoosePhoto";
    private Button btnOpenAbulm;
    private Button btnTakePhoto;
    private ImageView imgShow;
    public final int PHOTO_REQUEST_CODE = 1; //相册
    public final int CAMERA_REQUEST_CODE = 2; //相机
    public final int CROP_REQUEST_CODE = 3; //裁剪
    private static final String IMAGE_FILE_NAME = "chooseImage.jpg";

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
//        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //通过它来调用照相机拍照
//        startActivityForResult(takePhotoIntent, CAMERA_REQUEST_CODE);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
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
                //裁剪图片
                cropPic(imageUri);
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
                /*Bundle bundle = data.getExtras();
                Bitmap bitmap = ((Bitmap) bundle.get("data"));
                imgShow.setImageBitmap(bitmap);*/

                File cameraPhotoFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
                Log.d(TAG, "cameraPhotoFile:" + cameraPhotoFile.toString());
                Uri cameraPhotoUri = Uri.fromFile(cameraPhotoFile);
                Log.d(TAG, "cameraPhotoUri:" + cameraPhotoUri.toString());
                cropPic(cameraPhotoUri);
                break;
            case CROP_REQUEST_CODE:
                if(data != null) {
                    Bundle bundle2 = data.getExtras();
                    if(bundle2 != null) {
                        Bitmap bitmap2 = bundle2.getParcelable("data");
                        imgShow.setImageBitmap(bitmap2);
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //https://blog.csdn.net/wufeng55/article/details/80918749
    //裁剪图片
    private void cropPic(Uri data) {
        if(data == null) {
            return;
        }
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(data, "image/*");
        // 开启裁剪：打开的Intent所显示的View可裁剪
        cropIntent.putExtra("crop", "true");
        // 裁剪输出大小
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        /**
         * return-data
         * 这个属性决定我们在 onActivityResult 中接收到的是什么数据，
         * 如果设置为true 那么data将会返回一个bitmap
         * 如果设置为false，则会将图片保存到本地并将对应的uri返回，当然这个uri得有我们自己设定。
         * 系统裁剪完成后将会将裁剪完成的图片保存在我们所这设定这个uri地址上。我们只需要在裁剪完成后直接调用该uri来设置图片，就可以了。
         */
        cropIntent.putExtra("return-data", true);
        startActivityForResult(cropIntent, CROP_REQUEST_CODE);


    }
}
