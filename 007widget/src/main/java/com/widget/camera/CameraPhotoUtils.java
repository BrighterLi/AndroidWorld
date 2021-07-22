package com.widget.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;

import java.io.File;

import androidx.core.content.FileProvider;

public class CameraPhotoUtils {
    //注意，此处的provider名称需要和AndroidManifest.里面的大小写保持一致
    public static final String FILE_PROVIDER = ".FileProvider";

    public static void openCamera(final Activity mActivity, final String mFilePath, final int requestCode, final int permissionRequestCode) {
        new Handler(mActivity.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (PermissionCheckUtil.checkCameraAndStorage(mActivity)) {
                    Intent intent;
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机
                    File file = new File(mFilePath);
                    Uri outputUri = getCameraUri(mActivity, intent, file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
                    mActivity.startActivityForResult(intent, requestCode);
                } else {
                    PermissionCheckUtil.requestCameraAndStorage(mActivity, permissionRequestCode);
                }
            }
        });
    }

    public static void openAlbum(final Activity mActivity, final int requestJsKey, final int permissionRequestCode) {
        new Handler(mActivity.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (PermissionCheckUtil.checkReadAndWriteExternalStorage(mActivity)) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    mActivity.startActivityForResult(Intent.createChooser(intent, "选择图片"), requestJsKey);
                } else {
                    PermissionCheckUtil.requestReadAndWriteExternalStorage(mActivity, permissionRequestCode);
                }
            }
        });
    }

    public static Uri getCameraUri(Activity activity, Intent intent, File file) {
        if (activity == null || file == null) {
            return null;
        }
        Uri outputUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String pkName = activity.getPackageName();
            String allFileProvider = pkName + FILE_PROVIDER;
            outputUri = FileProvider.getUriForFile(activity, allFileProvider, file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            outputUri = Uri.fromFile(file);
        }
        return outputUri;
    }
}
