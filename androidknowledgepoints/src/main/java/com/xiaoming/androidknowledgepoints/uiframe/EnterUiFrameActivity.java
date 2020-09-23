package com.xiaoming.androidknowledgepoints.uiframe;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xiaoming.androidknowledgepoints.R;
import com.xiaoming.androidknowledgepoints.uiframe.recycleview.UiFrame1Activity;

public class EnterUiFrameActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_ui_frame);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_recyclerview:
                startActivity(new Intent(EnterUiFrameActivity.this, UiFrame1Activity.class));
                break;
        }
    }
}
