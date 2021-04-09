package com.tencent.qcloud.tim.uikit.modules.group.apply;

import android.app.Activity;
import android.os.Bundle;

import com.tencent.tuikit.R;
import com.tencent.qcloud.tim.uikit.modules.contact.FriendProfileLayout;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

import androidx.annotation.Nullable;

public class GroupApplyMemberActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_apply_member_activity);
        FriendProfileLayout layout = findViewById(R.id.friend_profile);

        layout.initData(getIntent().getSerializableExtra(TUIKitConstants.ProfileType.CONTENT));
    }

}
