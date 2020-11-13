package com.xiaoming.acrossendwebview.openh5.intercept;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.acrossendwebview.R;

public class ShouldOverrideUrlLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_should_override_url_loading);
    }
}
