package com.widget.loading;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.widget.R;

public class LinearDialogActivity extends AppCompatActivity {
    private Button btOpen;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_dialog);
        btOpen = findViewById(R.id.bt_linear_dialog_on);

        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(LinearDialogActivity.this);
                //依次设置标题,内容,是否用取消按钮关闭,是否显示进度
                progressDialog.setTitle("更新中");
                progressDialog.setMessage("正在更新中，请稍后...");
                progressDialog.setCancelable(true);
                //这里是设置进度条的风格,HORIZONTAL是水平进度条,SPINNER是圆形进度条
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(true);
                //调用show()方法将ProgressDialog显示出来
                progressDialog.show();
            }
        });
    }
}
