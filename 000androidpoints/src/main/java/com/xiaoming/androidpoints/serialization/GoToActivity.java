package com.xiaoming.androidpoints.serialization;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.serialization.parcelable.SerializationActivity;
import com.xiaoming.androidpoints.serialization.serializable.SerializableActivity;

//Android序列化总结：https://www.jianshu.com/p/208ac4a71c6f
public class GoToActivity extends AppCompatActivity {
    private Button mBtParcelable;
    private Button mSerializable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to);

        initView();
    }

    private void initView() {
        mBtParcelable = findViewById(R.id.bt_parcelable);
        mSerializable = findViewById(R.id.bt_serializable);

        mBtParcelable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoToActivity.this, SerializationActivity.class));
            }
        });

        mSerializable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoToActivity.this, SerializableActivity.class));
            }
        });
    }
}
