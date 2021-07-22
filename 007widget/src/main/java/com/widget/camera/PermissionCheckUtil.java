package com.widget.camera;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class PermissionCheckUtil {
    private static final Object LOCK = new Object();

    public static boolean checkCameraAndStorage(Activity context) {
        if (context == null) {
            return false;
        }
        synchronized (LOCK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                return ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
            } else {
                return ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
            }
        }
    }

    public static void requestCameraAndStorage(Activity mActivity, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
        } else {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA}, requestCode);
        }
    }

    public static boolean checkReadAndWriteExternalStorage(Activity context) {
        if (context == null) {
            return false;
        }
        synchronized (LOCK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED;
            } else {
                return true;
            }
        }
    }

    public static void requestReadAndWriteExternalStorage(Activity mActivity, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    requestCode);
        }
    }
}
