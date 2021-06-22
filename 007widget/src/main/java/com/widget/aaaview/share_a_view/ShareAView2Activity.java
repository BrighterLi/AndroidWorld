package com.widget.aaaview.share_a_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.airbnb.lottie.L;
import com.widget.R;

public class ShareAView2Activity extends AppCompatActivity {
    private LinearLayout mContainer;
    private Button mBtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_aview2);

        mContainer = findViewById(R.id.container);
        mBtBack = findViewById(R.id.bt_back);
        if(DataUtil.sShareVeiw != null && DataUtil.sShareVeiw.getParent() != null) {
            LinearLayout viewParent = (LinearLayout) DataUtil.sShareVeiw.getParent();
            viewParent.removeAllViewsInLayout();
            mContainer.addView(DataUtil.sShareVeiw);
        }

        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShareAView2Activity.this, ShareAViewActivity.class));
            }
        });

    }
}
