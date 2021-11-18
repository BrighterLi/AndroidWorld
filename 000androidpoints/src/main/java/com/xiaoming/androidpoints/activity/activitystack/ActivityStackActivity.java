package com.xiaoming.androidpoints.activity.activitystack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xiaoming.androidpoints.R;

public class ActivityStackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        startActivity(new Intent(ActivityStackActivity.this, Activity2.class));
    }
}
