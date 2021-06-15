package com.xiaoming.androidpoints.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.activity.fuzzification.background.BackGroundActivity;
import com.xiaoming.androidpoints.activity.onactivityresult.AActivity;

public class ActivityTestActivity extends Activity {
    private Button mBtOnActivityResult;
    private Button mBtActivityFuzzificaiton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_result_test);

        initView();
    }

    private void initView() {
        mBtOnActivityResult = findViewById(R.id.bt_on_activity_result);
        mBtOnActivityResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityTestActivity.this, AActivity.class));
            }
        });
        mBtActivityFuzzificaiton = findViewById(R.id.bt_activity_fuzzification);
        mBtActivityFuzzificaiton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ActivityTestActivity.this, FuzzificationActivity.class));
                startActivity(new Intent(ActivityTestActivity.this, BackGroundActivity.class));
            }
        });
    }
}
