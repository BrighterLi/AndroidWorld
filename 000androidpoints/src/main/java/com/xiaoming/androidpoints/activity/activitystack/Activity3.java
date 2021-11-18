package com.xiaoming.androidpoints.activity.activitystack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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


        List<Activity> activityList = ActivityStackUtil.getActivitiesByApplication(getApplication());
        List<Activity> finishActivity = new ArrayList<>();
        int activityIndex = 0;
        for(int i=0; i<activityList.size();i++) {
            Log.i("Activity3", "bright8#activity: " + activityList.get(i));
            if(activityList.get(i) instanceof ActivityStackActivity) {
                activityIndex = i;
                //activityList.get(i).finish();
            }
        }
        if(activityIndex > 0 && activityIndex <= activityList.size()) {
            for(int i= activityIndex; i<=activityList.size(); i++) {
                activityList.get(i).finish();
            }
        }
        finish();

    }
}
