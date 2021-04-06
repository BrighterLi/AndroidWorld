package com.xiaoming.androidpoints.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xiaoming.androidpoints.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NotificationPermissionActivity extends Activity {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_permission);
        int isNotificationEnabled = isNotificationEnabled(this);
        Toast.makeText(this, "isNotificationEnabled:" + isNotificationEnabled, Toast.LENGTH_SHORT).show();

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNotificationSetting(NotificationPermissionActivity.this,"", 1);
            }
        });
    }

    /**
     * @param context
     * @return -1 表示未知，0表示未开启，1表示开启
     */
    @SuppressLint("NewApi")
    public static int isNotificationEnabled(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                return NotificationManagerCompat.from(context).areNotificationsEnabled() ? 1 : 0;
            }
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            Class appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(appOps, value, uid, pkg)
                    == AppOpsManager.MODE_ALLOWED) ? 1 : 0;
        } catch (Throwable e) {

        }
        return -1;
    }

    public static void gotoNotificationSetting(Activity activity, String channelId, int requestCode) {
        try {
            String pkg = activity.getPackageName();
            if (Build.VERSION.SDK_INT >= 26) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, pkg);
                if (!TextUtils.isEmpty(channelId)) {
                    intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
                }
                activity.startActivityForResult(intent, requestCode);
            } else if (Build.VERSION.SDK_INT >= 19) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + pkg));
                activity.startActivityForResult(intent, requestCode);
            } else {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivityForResult(intent, requestCode);
            }
        } catch (Exception e) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivityForResult(intent, requestCode);
        }
    }

}

