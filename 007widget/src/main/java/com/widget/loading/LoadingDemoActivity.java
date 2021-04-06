package com.widget.loading;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.widget.R;

//原生的Loading控件 ProgressDialog
//https://blog.csdn.net/lpCrazyBoy/article/details/80758619
public class LoadingDemoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btNormalDialog;
    private Button btLinearDialog;
    private Button btLinearDialogUpdate;
    private Button btProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_demo);
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
                startActivity(new Intent(LoadingDemoActivity.this, NormalDialogActivity.class));
                break;
            case R.id.bt_linear_dialog:
                startActivity(new Intent(LoadingDemoActivity.this, LinearDialogActivity.class));
                break;
            case R.id.bt_linear_dialog_update:
                startActivity(new Intent(LoadingDemoActivity.this, LinearDialogUpdateActivity.class));
                break;
            case R.id.bt_progressbar:
                startActivity(new Intent(LoadingDemoActivity.this, ProgressBarActivity.class));
                break;
        }
    }
}