package com.xiaoming.androidpoints.permission;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xiaoming.androidpoints.R;

//单个权限申请
//https://blog.csdn.net/man_help/article/details/52316501
public class OnePermissionActivity extends Activity {
    private Button btAskPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_permission);
        btAskPermission = findViewById(R.id.bt_ask_permission);
        btAskPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermission();
            }
        });
    }

    private void askPermission() {
        //检查是否有对应权限
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //如果没有则请求该权限
            Toast.makeText(this, "无此权限，进行申请", Toast.LENGTH_SHORT).show();
            //此方法异步执行，会弹出权限请求对话框，让用户授权，并回调 onRequestPermissionsResult 来告知授权结果
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);
        } else { //有该权限
            //则可以做对应的事情
            Toast.makeText(this, "已有该权限", Toast.LENGTH_SHORT).show();
        }
    }

    //权限请求用户选择后的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                //用户同意了该权限,可以做对应需要授权的事情
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "用户同意了该权限", Toast.LENGTH_SHORT ).show();
                    //doSomething();
                } else { //用户拒绝了该权限
                    Toast.makeText(this, "用户拒绝了该权限", Toast.LENGTH_SHORT).show();

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //判断用户是否选择拒绝,则返回true;拒绝+不再询问，允许，重来没有询问过都返回false
                        boolean shouldShow = shouldShowRequestPermissionRationale(permissions[0]);
                        if(!shouldShow) {
                            //需要弹出自定义对话框，引导用户去应用的设置界面手动开启权限
                            Toast.makeText(this, "需要去设置界面打开该权限", Toast.LENGTH_SHORT).show();
                            showAskPermissionDialog();
                        }
                    }
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //需要手动开启缺失的权限对话框
    private void showAskPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前权限不足。\n\n可点击\"设置\"-\"权限\"-打开所需权限。\n\n最后点击两次后退按钮，即可返回。");
        builder.setNegativeButton("知道了", null);
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    //启动应用的设置 来手动开启权限
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
}