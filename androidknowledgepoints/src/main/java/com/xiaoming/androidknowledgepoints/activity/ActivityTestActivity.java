package com.xiaoming.androidknowledgepoints.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidknowledgepoints.R;
import com.xiaoming.androidknowledgepoints.activity.onactivityresult.AActivity;

public class ActivityTestActivity extends Activity {
    private Button mBtOnActivityResult;

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
    }
}
