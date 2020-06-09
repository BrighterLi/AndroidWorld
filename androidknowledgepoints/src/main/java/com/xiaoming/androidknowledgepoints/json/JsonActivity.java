package com.xiaoming.androidknowledgepoints.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.androidknowledgepoints.R;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        JsonUtil.toJson();
    }
}
