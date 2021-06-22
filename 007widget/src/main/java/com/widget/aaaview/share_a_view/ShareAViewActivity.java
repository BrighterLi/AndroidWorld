package com.widget.aaaview.share_a_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.widget.R;

public class ShareAViewActivity extends AppCompatActivity {
    private TextView mTextView;
    private Button mBtJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_aview);

        mTextView = findViewById(R.id.textview);
        mBtJump = findViewById(R.id.bt_jump_to);
        DataUtil.sShareVeiw = mTextView;

        mBtJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShareAViewActivity.this, ShareAView2Activity.class));
            }
        });
    }
}
