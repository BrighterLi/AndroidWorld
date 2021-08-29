package com.widget.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.widget.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //获取通知管理器
        NotificationManager notificationManager = ((NotificationManager) getSystemService(NOTIFICATION_SERVICE));

        //创建一个通知
        String name = "name";
        String id = "1";
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this)
                    .setChannelId(id)
                    .setContentTitle("活动")
                    .setContentText("您有一项新活动")
                    .setSmallIcon(R.drawable.ic_launcher_foreground).build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("活动")
                    .setContentText("您有一项新活动")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setOngoing(true)
                    .setChannelId(id);//无效
            notification = notificationBuilder.build();
        }

        //发布通知
        notificationManager.notify(1, notification);
    }

}
