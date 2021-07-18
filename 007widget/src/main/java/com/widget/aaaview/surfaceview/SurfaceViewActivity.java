package com.widget.aaaview.surfaceview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.widget.R;

public class SurfaceViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_surface_view);
        //setContentView(new MSurfaceView(this));
        //setContentView(new MySurface(this, 600, 1000));
        setContentView(new MysxView(this));
    }
}
