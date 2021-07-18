package com.widget.aaaview.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.widget.R;

public class AlphaActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);

        mTextView = findViewById(R.id.tv_alpha);
        //mTextView.getBackground().setAlpha(0);
        //mTextView.getBackground().setAlpha(100);
        //mTextView.getBackground().setAlpha(255);
        mTextView.setAlpha(0.5f);
    }
}
