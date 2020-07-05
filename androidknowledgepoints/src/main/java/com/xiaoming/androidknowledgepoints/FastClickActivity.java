package com.xiaoming.androidknowledgepoints;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//防止快速点击
public class FastClickActivity extends AppCompatActivity {
    private final static String TAG = "FastClickActivity";
    private Button fastClickButton;
    private Button fastClickButton2;
    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_click);

        fastClickButton = findViewById(R.id.btn_fast_click);
        fastClickButton2 = findViewById(R.id.btn_fast_click2);

        //单个控件防止快速点击
        fastClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                if(now - lastClickTime > 1000) {
                    lastClickTime = now;
                    Log.d(TAG,"正常点击");
                } else {
                    Log.d(TAG, "点得太快了");
                    Toast.makeText(FastClickActivity.this, "点得太快了", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //防止快速点击，全局
        fastClickButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FastClickUtils.isFastClick()) {
                    Toast.makeText(FastClickActivity.this, "点得太快了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
