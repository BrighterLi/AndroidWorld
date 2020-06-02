package com.xiaoming.net.packegenet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoming.net.R;

import java.util.Map;

public class PackegeNetDetectActivity extends AppCompatActivity {
    private static final String TAG = "PackegeNetDetect";
    private Button mBtStart;
    private TextView mTvShow;
    private StringBuilder stringBuilder = new StringBuilder();
    private Map<String, Object> netInfosMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packege_net_detect);

        mTvShow = findViewById(R.id.tv_show);
        mBtStart = findViewById(R.id.bt_start);

        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "bright#onClick1");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "bright#onClick2");
                        netInfosMap = AppFaultDiagnosisUtils.getNetInfos();
                        Log.d(TAG, "bright#onClick3");
                        for (String key : netInfosMap.keySet()) {
                            stringBuilder.append("\n" + key + "—" + netInfosMap.get(key) );
                            Log.d(TAG,  "\n" + "bright2#" + key + "—" + netInfosMap.get(key));
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvShow.setText(stringBuilder.toString());
                                Log.d(TAG, "bright#onClick4");
                            }
                        });

                    }
                }).start();
            }
        });
    }
}
