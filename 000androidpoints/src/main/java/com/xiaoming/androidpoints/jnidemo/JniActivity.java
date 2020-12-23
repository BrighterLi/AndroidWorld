package com.xiaoming.androidpoints.jnidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaoming.androidpoints.R;


//Android JNI学习——Demo演示:https://www.jianshu.com/p/0f34c097028a
public class JniActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnAdd, btnSub, btnMul, btnDiv;
    private EditText inputA, inputB;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);

        setUpView();
        addListener();
    }

    private void addListener() {
        btnAdd.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnSub.setOnClickListener(this);
    }

    private void setUpView() {
        btnAdd = this.findViewById(R.id.add);
        btnSub = this.findViewById(R.id.sub);
        btnMul = this.findViewById(R.id.mul);
        btnDiv = this.findViewById(R.id.div);

        inputA = this.findViewById(R.id.input_a);
        inputB = this.findViewById(R.id.input_b);

        tvResult = this.findViewById(R.id.result);
    }

    @Override
    public void onClick(View view) {
        double result = 0;
        String strA = inputA.getText().toString();
        String strB = inputB.getText().toString();
        int a = Integer.parseInt(strA);
        int b = Integer.parseInt(strB);
        switch (view.getId()) {
            case R.id.add:
                result = JNITools.add(a, b);
                break;
            case R.id.sub:
                result = JNITools.sub(a, b);
                break;
            case R.id.mul:
                result = JNITools.mul(a, b);
                break;
            case R.id.div:
                result = JNITools.div(a, b);
                break;
        }
        tvResult.setText("" + result);
    }
}
