package com.xiaoming.androidpoints.datastorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.datastorage.File.FileActivity;
import com.xiaoming.androidpoints.datastorage.cp.CPActivity;
import com.xiaoming.androidpoints.datastorage.cp.demo2.CpActivity2;
import com.xiaoming.androidpoints.datastorage.room.RoomTestActivity;
import com.xiaoming.androidpoints.datastorage.room.roomandflow.RoomFlowActivity;
import com.xiaoming.androidpoints.datastorage.sqlite.SqliteActivity;

public class DataStorageActivity extends Activity implements View.OnClickListener{
    private Button mBtFile;
    private Button mBtCP;
    private Button mBtRoom;
    private Button mSqlite;

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
        mBtRoom = findViewById(R.id.bt_room);
        mBtRoom.setOnClickListener(this);
        mSqlite = findViewById(R.id.bt_sqlite);
        mSqlite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_file:
                startActivity(new Intent(DataStorageActivity.this, FileActivity.class));
                break;
            case R.id.bt_cp:
                //startActivity(new Intent(DataStorageActivity.this, CPActivity.class));
                startActivity(new Intent(DataStorageActivity.this, CpActivity2.class));
                break;
            case R.id.bt_room:
                //startActivity(new Intent(DataStorageActivity.this, RoomTestActivity.class));
                startActivity(new Intent(DataStorageActivity.this, RoomFlowActivity.class));
                break;
            case R.id.bt_sqlite:
                startActivity(new Intent(DataStorageActivity.this, SqliteActivity.class));
                break;
        }
    }
}
