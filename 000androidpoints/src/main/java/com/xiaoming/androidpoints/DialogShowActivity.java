package com.xiaoming.androidpoints;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DialogShowActivity extends AppCompatActivity {
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_show);

        btnClick = findViewById(R.id.btn_click);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    //在子线程中弹出Toast，会报错
    //解决方式：先调用Looper.prepare();再调用Toast.makeText().show();最后再调用Looper.loop();
    private void showCustomDialog() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(DialogShowActivity.this, "111111111", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }
}
