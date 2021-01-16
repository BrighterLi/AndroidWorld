package com.xiaoming.androidpoints.permission;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xiaoming.androidpoints.R;

import java.util.ArrayList;

//多个权限申请
//https://blog.csdn.net/man_help/article/details/52316501
//申请多个权限和申请单个权限的步骤是一样的，可先检查去除某些已授权过的，不重复申请，
//然后系统会在弹窗中依次展示要申请的权限，用户都选择后，结果也可在回调方法中处理
public class MultiPermissionActivity extends Activity {
    private final String TAG = "MultiPermissionActivity";
    private Button btAskPermission;
    private final int MUILTI_PERMISSIONS_ASK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_permission);
        btAskPermission = findViewById(R.id.bt_ask_permission);
        btAskPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //申请 读联系人、读短信、获取定位的权限
                String[] askPermissionsList = new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS, Manifest.permission.ACCESS_FINE_LOCATION};
                //需要请求授权的权限
                ArrayList<String> needRequstList = new ArrayList<>();
                //遍历 过滤已授权的权限，防止重复申请
                for(String permisson: askPermissionsList) {
                    int check = checkSelfPermission(permisson);
                    if(check != PackageManager.PERMISSION_GRANTED) {
                        needRequstList.add(permisson);
                    }
                }

                //之前没有全部授权
                if(needRequstList.size() > 0) {
                    //请求权限，此方法异步执行，会弹出权限请求对话框，让用户授权，并回调 onRequestPermissionsResult 来告知授权结果
                    requestPermissions(needRequstList.toArray(new String[needRequstList.size()]),MUILTI_PERMISSIONS_ASK); //List转成数组
                    Toast.makeText(MultiPermissionActivity.this, "请求权限", Toast.LENGTH_SHORT).show();
                } else { //之前就已经全部授权
                    //可以做相关的需要这些权限才能做的事情
                    //doSomething();
                    Toast.makeText(MultiPermissionActivity.this, "权限之前就已经全部授权", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MUILTI_PERMISSIONS_ASK:
                if(grantResults.length > 0) {
                    //被拒绝的权限列表
                    ArrayList<String> deniiedPermissions = new ArrayList<>();
                    for(int i = 0; i < grantResults.length; i++) {
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            deniiedPermissions.add(permissions[i]);
                            Log.d(TAG, "deniedPermission:"+ permissions[i]);
                        }
                    }

                    if(deniiedPermissions.size() <= 0) { //用户全部授权
                        //doSomethings();
                        Toast.makeText(this, "权限已全部被用户授权", Toast.LENGTH_SHORT).show();
                    } else { //用户没有全部授权
                        Toast.makeText(MultiPermissionActivity.this, "用户没有全部授权", Toast.LENGTH_SHORT).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            //引导用户手动开启权限列表
                            ArrayList<String> needShow = new ArrayList<>();
                            //从没有授权的权限中判断是否需要引导用户，用户点击了拒绝且不再提醒则需要引导用户
                            for(int i=0; i < deniiedPermissions.size(); i++) {
                                String permission = deniiedPermissions.get(i);
                                if(!shouldShowRequestPermissionRationale(permission)) {
                                    needShow.add(permission);
                                    Log.d(TAG, "needShow:" + permission);
                                }
                            }

                            //需要引导用户
                            if(needShow.size() > 0) {
                                showAskPermissionDialog();
                            }
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

