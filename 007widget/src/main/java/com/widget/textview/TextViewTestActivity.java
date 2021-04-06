package com.widget.textview;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.widget.R;

public class TextViewTestActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_test);

        mBt1 = findViewById(R.id.bt1);
        mBt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                startActivity(new Intent(TextViewTestActivity.this, TextView1Activity.class));
                break;
        }
    }
}
