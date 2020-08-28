package com.xiaoming.androidknowledgepoints.ajavaknowledge.localvarible;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidknowledgepoints.R;

public class LocalVaribleActivity extends AppCompatActivity {
    private Button mBtVarible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_varible);

        initView();
    }

    private void initView() {
        mBtVarible = findViewById(R.id.bt_local_value);

        mBtVarible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalVarible.main();
            }
        });
    }
}
