package com.xiaoming.androidknowledgepoints.datastorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidknowledgepoints.R;
import com.xiaoming.androidknowledgepoints.datastorage.File.FileActivity;

public class DataStorageActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);

        initView();
    }

    private void initView() {
        mBtFile = findViewById(R.id.bt_file);
        mBtFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_file:
                startActivity(new Intent(DataStorageActivity.this, FileActivity.class));
                break;
        }
    }
}
