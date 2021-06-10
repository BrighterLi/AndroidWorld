package com.widget.aaaview.custom_view.fish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.widget.R;

public class FishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);
       /* ImageView iv = findViewById(R.id.iv_fish);
        iv.setImageDrawable(new FishDrawable());*/
    }
}
