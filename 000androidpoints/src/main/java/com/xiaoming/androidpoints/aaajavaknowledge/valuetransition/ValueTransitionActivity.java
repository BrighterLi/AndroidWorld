package com.xiaoming.androidpoints.aaajavaknowledge.valuetransition;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;

public class ValueTransitionActivity extends Activity {
    private Button mBtValueTransiton;
    private Button mBtValueTransiton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_transition);

        initView();
    }

    private void initView() {
        mBtValueTransiton = findViewById(R.id.bt_value_transition);
        mBtValueTransiton2 = findViewById(R.id.bt_value_transition2);

        mBtValueTransiton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueTransition.main();
            }
        });

        mBtValueTransiton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueTransition2.main();
            }
        });
    }
}
