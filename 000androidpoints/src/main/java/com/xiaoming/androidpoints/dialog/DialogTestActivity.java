package com.xiaoming.androidpoints.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.dialog.fullscreendialog.FullScreenDialog;

public class DialogTestActivity extends Activity {
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);

        btnClick = findViewById(R.id.btn_click);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullScreenDialog();
            }
        });
    }


    private void showFullScreenDialog() {
        FullScreenDialog fullScreenDialog = new FullScreenDialog(this);
        fullScreenDialog.showDialog();
        fullScreenDialog.mTvContentTop.setText("更新标题");
        fullScreenDialog.mContentButton1.setVisibility(View.VISIBLE);
        fullScreenDialog.mTvContent1.setText("更新按钮1");
        fullScreenDialog.mContentButton2.setVisibility(View.VISIBLE);
        fullScreenDialog.mTvContent2.setText("更新按钮2");
        fullScreenDialog.mContentButton3.setVisibility(View.VISIBLE);
        fullScreenDialog.mTvContent3.setText("更新按钮3");
        fullScreenDialog.mContentBottom.setVisibility(View.VISIBLE);
        fullScreenDialog.mTvContentBottom.setText("更新底部按钮");

        fullScreenDialog.mContentButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogTestActivity.this, "点击了按钮1", Toast.LENGTH_SHORT).show();
            }
        });
        fullScreenDialog.mContentButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogTestActivity.this, "点击了按钮2", Toast.LENGTH_SHORT).show();
            }
        });
        fullScreenDialog.mContentButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogTestActivity.this, "点击了按钮3", Toast.LENGTH_SHORT).show();
            }
        });
        fullScreenDialog.mTvContentBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogTestActivity.this, "点击了底部按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
