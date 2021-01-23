package com.widget.loading;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.widget.R;

public class NormalDialogActivity extends AppCompatActivity {
    private Button btTurnOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_dialog);

        btTurnOn = findViewById(R.id.bt_normal_dialog_on);

        btTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //参数依次为,上下文,标题,内容,是否显示进度,是否可以用取消按钮关闭
                ProgressDialog.show(NormalDialogActivity.this,"资源加载中","资源加载中，请稍后...",false,true);
            }
        });
    }
}
