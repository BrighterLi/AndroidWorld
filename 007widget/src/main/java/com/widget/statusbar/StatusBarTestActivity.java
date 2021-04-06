package com.widget.statusbar;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.widget.R;


public class StatusBarTestActivity extends AppCompatActivity {
    private Button mBtImmersedStatusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_test);

        initView();
    }

    private void initView() {
        mBtImmersedStatusBar = findViewById(R.id.bt_immersed_status_bar);
        mBtImmersedStatusBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatusBarTestActivity.this, ImmerseStatusBarActivity.class));
            }
        });
    }
}
