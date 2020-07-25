package com.fenqile.functionfacedetect3cameraapi2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class RecordActivityWithSurfaceView extends Activity {
    private Button mBtStartRecord;
    private Button mBtStopRecord;
    private SurfaceView mSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_with_surface_view);

        initView();
    }

    private void initView() {
        mBtStartRecord = findViewById(R.id.bt_start_record);
        mBtStopRecord = findViewById(R.id.bt_stop_record);
        mSurfaceView = findViewById(R.id.surface_view);

        mBtStartRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBtStopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
