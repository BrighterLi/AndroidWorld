package com.xiaoming.function;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.function.androidknowledgepoints.AndroidKnowledgePointsActivity;
import com.xiaoming.function.method.MethodsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnMethod;
    private Button btnAndroidKnowledge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnMethod = findViewById(R.id.btn_method);
        btnAndroidKnowledge = findViewById(R.id.btn_android_knowledge);

        btnMethod.setOnClickListener(this);
        btnAndroidKnowledge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_method:
                startActivity(new Intent(MainActivity.this, MethodsActivity.class));
                break;
            case R.id.btn_android_knowledge:
                startActivity(new Intent(MainActivity.this, AndroidKnowledgePointsActivity.class));
                break;
        }
    }
}
