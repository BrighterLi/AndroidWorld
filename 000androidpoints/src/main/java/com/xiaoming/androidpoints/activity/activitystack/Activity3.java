package com.xiaoming.androidpoints.activity.activitystack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.xiaoming.androidpoints.R;

import java.util.ArrayList;
import java.util.List;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        //getStackActivity();
        getStackActivity2();


    }

    private void getStackActivity() {
        List<Activity> activityList = ActivityStackUtil.getActivitiesByApplication(getApplication());
        List<Activity> finishActivity = new ArrayList<>();
        int activityIndex = 0;
        for(int i=0; i<activityList.size();i++) {
            Log.i("Activity3", "bright8#activity: " + activityList.get(i));
            if(activityList.get(i) instanceof ActivityStackActivity) {
                activityIndex = i;
                activityList.get(i).finish();
            }
        }
        /*if(activityIndex > 0 && activityIndex <= activityList.size()) {
            for(int i= activityIndex; i<=activityList.size(); i++) {
                activityList.get(i).finish();
            }
        }
        finish();*/
    }

    //Android 获取activity栈中activity: https://blog.csdn.net/yaojie5519/article/details/82252439
    private void getStackActivity2() {
        ActivityManager manager = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        String packageName = info.topActivity.getPackageName();
        String topClassName = info.topActivity.getClassName();
        String baseClassName = info.baseActivity.getClassName();
        int activityNum = info.numActivities;
        Log.i("Activity3", "bright8#packageName: " + packageName + "\ntopClassName: " + topClassName
                + "\nbaseClassName: " + baseClassName+ "\nactivityNum: " + activityNum);
    }
}
