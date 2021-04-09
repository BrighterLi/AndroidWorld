package com.tencent.tecentim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tencent.tecentim.callback.LoginCallBack;
import com.tencent.tecentim.view.card.TranslucentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   private Button mBtIm;
   private Button mBtTranslucent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtIm = findViewById(R.id.bt_im);
        mBtTranslucent = findViewById(R.id.bt_translucent_activity);
        mBtIm.setOnClickListener(this);
        mBtTranslucent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_im:
                startActivity(new Intent(MainActivity.this, ImTestActivity.class));
                break;
            case R.id.bt_translucent_activity:
                startActivity(new Intent(MainActivity.this, TranslucentActivity.class));
                break;
        }
    }
}
