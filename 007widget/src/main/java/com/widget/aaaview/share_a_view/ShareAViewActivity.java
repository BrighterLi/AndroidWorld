package com.widget.aaaview.share_a_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.widget.R;

public class ShareAViewActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_aview);

        mTextView = findViewById(R.id.textview);
        DataUtil.sShareVeiw = mTextView;
        startActivity(new Intent(ShareAViewActivity.this, ShareAView2Activity.class));
    }
}
