package com.xiaoming.androidpoints.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.androidpoints.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class PermissionActivity extends Activity {
    private ListView listView;
    private List<String> demoListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initData();
        listView = findViewById(R.id.lv_main);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(PermissionActivity.this, OnePermissionActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(PermissionActivity.this, MultiPermissionActivity.class));
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        startActivity(new Intent(PermissionActivity.this,NotificationPermissionActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });

        //适配器
        ArrayAdapter arrayAdapter = new ArrayAdapter(PermissionActivity.this, android.R.layout.simple_list_item_1,demoListData);
        //绑定适配器,相当于将数据放入到view
        listView.setAdapter(arrayAdapter);
    }

    private void initData() {
        demoListData.add("单个权限申请");
        demoListData.add("多个权限申请");
        demoListData.add("使用权限第三库");

        demoListData.add("自定义弹出的Dialog提示框");

        demoListData.add("通知权限判断");
    }

    public final void requestPermissions(@NonNull String[] permissions, int requestCode) {
        if (requestCode < 0) { //requestCode 必须为正数
            throw new IllegalArgumentException("requestCode should be >= 0");
        }
        if (mHasCurrentPermissionsRequest) {
            Log.w(TAG, "Can reqeust only one set of permissions at a time");
            // Dispatch the callback with empty arrays which means a cancellation.
            onRequestPermissionsResult(requestCode, new String[0], new int[0]);
            return;
        }
        Intent intent = getPackageManager().buildRequestPermissionsIntent(permissions);
        startActivityForResult(REQUEST_PERMISSIONS_WHO_PREFIX, intent, requestCode, null);
        mHasCurrentPermissionsRequest = true;
    }

    public Intent buildRequestPermissionsIntent(@NonNull String[] permissions) {
        if (ArrayUtils.isEmpty(permissions)) {
            throw new IllegalArgumentException("permission cannot be null or empty");
        }
        Intent intent = new Intent(ACTION_REQUEST_PERMISSIONS);
        intent.putExtra(EXTRA_REQUEST_PERMISSIONS_NAMES, permissions);
        intent.setPackage(getPermissionControllerPackageName());
        return intent;
    }
}
