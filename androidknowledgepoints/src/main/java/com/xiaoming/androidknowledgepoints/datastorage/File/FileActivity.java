package com.xiaoming.androidknowledgepoints.datastorage.File;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidknowledgepoints.R;

public class FileActivity extends Activity implements View.OnClickListener{
    private Button mBtNewFolder;
    private Button mBtNewFile;
    private Button mBtWriteData;
    private Button mBtContinueWrite;
    private Button mBtReadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        FileUtil.verifyStoragePermission(this);

        initView();
        FileUtil.getFilePath(FileActivity.this);
    }

    private void initView() {
        mBtNewFolder = findViewById(R.id.bt_new_folder);
        mBtNewFile = findViewById(R.id.bt_new_file);
        mBtWriteData = findViewById(R.id.bt_write_data);
        mBtContinueWrite = findViewById(R.id.bt_continue_write_data);
        mBtReadData = findViewById(R.id.bt_read_data);

        mBtNewFolder.setOnClickListener(this);
        mBtNewFile.setOnClickListener(this);
        mBtWriteData.setOnClickListener(this);
        mBtContinueWrite.setOnClickListener(this);
        mBtReadData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_new_folder:
                FileUtil.createFolder("hello333");
                break;
            case R.id.bt_new_file:
                FileUtil.createFile("world5555");
                break;
            case R.id.bt_write_data:
                //FileUtil.writeData("world5555");
                break;
            case R.id.bt_continue_write_data:
                //FileUtil.continueWrite("world5555");
                break;
            case R.id.bt_read_data:
                //FileUtil.readFile("world5555");
                FileUtil.getStrFromAssetsFile(this);
                break;
        }
    }
}
