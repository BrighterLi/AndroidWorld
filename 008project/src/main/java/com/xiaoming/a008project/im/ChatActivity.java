package com.xiaoming.a008project.im;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.xiaoming.a008project.R;

public class ChatActivity extends AppCompatActivity {
    private ChatLayout mChatLayout;
    private ChatInfo mChatInfo;
    private TitleBarLayout mTitleBar;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", 0);
        initView();
    }

    private void initView() {
        mChatLayout = findViewById(R.id.chat_layout);
        //单聊组件的默认UI和交互初始化
        mChatLayout.initDefault();
        mChatInfo = new ChatInfo();
        mChatInfo.setId("11111");
        mChatInfo.setType(V2TIMConversation.V2TIM_C2C);
        mChatInfo.setChatName("聊天");
        mChatLayout.setChatInfo(mChatInfo);
        //获取单聊面板的标题栏
        mTitleBar = mChatLayout.getTitleBar();
    }
}
