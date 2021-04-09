package com.tencent.qcloud.tim.uikit.modules.group.info;

import android.os.Bundle;

import com.tencent.tuikit.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class GroupInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_info_activity);
        GroupInfoFragment fragment = new GroupInfoFragment();
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.group_manager_base, fragment).commitAllowingStateLoss();
    }

    @Override
    public void finish() {
        super.finish();
        setResult(1001);
    }
}
