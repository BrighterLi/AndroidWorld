package com.xiaoming.androidpoints.datastorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.datastorage.File.FileActivity;
import com.xiaoming.androidpoints.datastorage.cp.CPActivity;
import com.xiaoming.androidpoints.datastorage.room.RoomActivity;

public class DataStorageActivity extends Activity implements View.OnClickListener{
    private Button mBtFile;
    private Button mBtCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);

        initView();
    }

    private void initView() {
        mBtFile = findViewById(R.id.bt_file);
        mBtFile.setOnClickListener(this);
        mBtCP = findViewById(R.id.bt_cp);
        mBtCP.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_file:
                startActivity(new Intent(DataStorageActivity.this, FileActivity.class));
                break;
            case R.id.bt_cp:
                startActivity(new Intent(DataStorageActivity.this, CPActivity.class));
                break;
            case R.id.bt_room:
                startActivity(new Intent(DataStorageActivity.this, RoomActivity.class));
                break;
        }
    }
}
