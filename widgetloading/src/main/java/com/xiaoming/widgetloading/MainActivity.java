package com.xiaoming.widgetloading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//原生的Loading控件 ProgressDialog
//https://blog.csdn.net/lpCrazyBoy/article/details/80758619
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btNormalDialog;
    private Button btLinearDialog;
    private Button btLinearDialogUpdate;
    private Button btProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btNormalDialog = findViewById(R.id.bt_normal_dialog);
        btLinearDialog = findViewById(R.id.bt_linear_dialog);
        btLinearDialogUpdate = findViewById(R.id.bt_linear_dialog_update);
        btProgressBar = findViewById(R.id.bt_progressbar);

        //注册监听
        btNormalDialog.setOnClickListener(this);
        btLinearDialog.setOnClickListener(this);
        btLinearDialogUpdate.setOnClickListener(this);
        btProgressBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_normal_dialog:
                startActivity(new Intent(MainActivity.this, NormalDialogActivity.class));
                break;
            case R.id.bt_linear_dialog:
                startActivity(new Intent(MainActivity.this,LinearDialogActivity.class));
                break;
            case R.id.bt_linear_dialog_update:
                startActivity(new Intent(MainActivity.this, LinearDialogUpdateActivity.class));
                break;
            case R.id.bt_progressbar:
                startActivity(new Intent(MainActivity.this, ProgressBarActivity.class));
                break;
        }
    }
}
