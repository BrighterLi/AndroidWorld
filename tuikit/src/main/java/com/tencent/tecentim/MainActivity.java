package com.tencent.tecentim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tecentim.callback.LoginCallBack;
import com.tencent.tecentim.view.card.IncludeTestActivity;
import com.tencent.tecentim.view.card.TranslucentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   private Button mBtIm;
   private Button mBtTranslucent;
   private Button mBtTest;
   private Button mBtInclude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtIm = findViewById(R.id.bt_im);
        mBtTranslucent = findViewById(R.id.bt_translucent_activity);
        mBtTest = findViewById(R.id.bt_test);
        mBtInclude = findViewById(R.id.bt_include);
        mBtIm.setOnClickListener(this);
        mBtTranslucent.setOnClickListener(this);
        mBtInclude.setOnClickListener(this);
        mBtTest.setOnClickListener(this);
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
            case R.id.bt_include:
                startActivity(new Intent(MainActivity.this, IncludeTestActivity.class));
                break;
            case R.id.bt_test:
                Toast.makeText(this, "combination_to_left_righht", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
