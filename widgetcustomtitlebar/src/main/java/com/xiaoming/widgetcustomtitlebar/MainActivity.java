package com.xiaoming.widgetcustomtitlebar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CustomTitleBar customTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customTitleBar = findViewById(R.id.titleBar);
        customTitleBar.setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击左边图标",Toast.LENGTH_SHORT).show();
            }
        });

        customTitleBar.setRightIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击右边图标",Toast.LENGTH_SHORT).show();
            }
        });

        customTitleBar.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击右边文字",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
