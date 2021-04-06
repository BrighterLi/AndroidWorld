package com.fenqile.functionfacedetect3cameraapi2;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends Activity implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = "MainActivity";
    private static int RC_READ_EXTERNAL_STORAGE = 2;
    private Button mBtReocrodWithTextureView;
    private Button mBtReocrodWithSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inquirePermissions(this);
        initView();
    }

    public static void inquirePermissions(Activity activity) {
        if (!EasyPermissions.hasPermissions(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        )) {
            EasyPermissions.requestPermissions(
                    activity,
                    "申请人脸检测相关权限",
                    RC_READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO //音频录制
            );
        } else {
        }
    }

    private void initView() {
        mBtReocrodWithSurfaceView = findViewById(R.id.bt_record_surfaceview);
        mBtReocrodWithTextureView = findViewById(R.id.bt_record_textureview);

        mBtReocrodWithSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecordActivityWithSurfaceView.class));
            }
        });

        mBtReocrodWithTextureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecordActivitywithTextureView.class));
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "禁止", Toast.LENGTH_SHORT).show();
    }
}
