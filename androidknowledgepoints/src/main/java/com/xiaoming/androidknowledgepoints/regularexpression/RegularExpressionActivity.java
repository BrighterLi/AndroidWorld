package com.xiaoming.androidknowledgepoints.regularexpression;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoming.androidknowledgepoints.R;

public class RegularExpressionActivity extends AppCompatActivity {
    private Button mBtRegular;
    private EditText mEtRetular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_expression);

        initView();
    }

    private void initView() {
        mBtRegular = findViewById(R.id.bt_regular);
        mEtRetular = findViewById(R.id.et_regular_input);

        mBtRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = mEtRetular.getText().toString();
                boolean isMatch = RegularUtil.match4(inputStr);
                Toast.makeText(RegularExpressionActivity.this, "" + isMatch, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
