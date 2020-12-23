package com.xiaoming.androidpoints.activity.onactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.xiaoming.androidpoints.R;

public class AActivity extends Activity {
    public final int REQUEST_CODE_B = 1;
    public final int REQUEST_CODE_C = 2;

    private TextView tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Button btn_b = (Button) findViewById(R.id.bt1);
        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AActivity.this, BActivity.class);
                startActivityForResult(intent, REQUEST_CODE_B);
                //REQUEST_CODE用于辨别调用的是那个Activity
            }
        });

        Button btn_c = (Button) findViewById(R.id.bt2);
        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AActivity.this, CActivity.class);
                startActivityForResult(intent, REQUEST_CODE_C);
                //REQUEST_CODE用于辨别调用的是那个Activity
            }
        });

        tt = (TextView) findViewById(R.id.text);
    }


    /**
     * requestCode和startActivityForResult中的requestCode相对应
     * resultCode和Intent是由子Activity通过其setResult()方法返回
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_B: //返回的结果是来自于Activity B
                if (resultCode == Activity.RESULT_OK) {
                    tt.setText(data.getStringExtra("respond"));
                } else {
                    tt.setText("What?Nobody?");
                }
                break;
            case REQUEST_CODE_C: //返回的结果是来自于Activity C
                if (resultCode == Activity.RESULT_OK) {
                    tt.setText(data.getStringExtra("respond"));
                } else {
                    tt.setText("What?Nobody?");
                }
                break;
            default:
                break;
        }
    }
}

