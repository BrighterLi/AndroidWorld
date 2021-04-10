package com.tencent.tecentim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil;
import com.tencent.tecentim.messagehelper.ChatLayoutHelper;

public class ChatActivity extends AppCompatActivity {
    private ChatLayout mChatLayout;
    private ChatInfo mChatInfo;
    private TitleBarLayout mTitleBar;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        Log.i("ChatActivity", "bright8#聊天对象userId:" + userId);
        initView();

        ChatLayoutHelper helper = new ChatLayoutHelper(this);
        helper.customizeChatLayout(mChatLayout);
    }

    private void initView() {
        mChatLayout = findViewById(R.id.chat_layout);
        //单聊组件的默认UI和交互初始化
        mChatLayout.initDefault();
        mChatInfo = new ChatInfo();
        mChatInfo.setId(userId);
        mChatInfo.setType(V2TIMConversation.V2TIM_C2C);
        mChatInfo.setChatName(userId);
        mChatLayout.setChatInfo(mChatInfo);
        //获取单聊面板的标题栏
        mTitleBar = mChatLayout.getTitleBar();
    }


    private void buildCustomMessage() {
        String str = "{\"text\": \"欢迎加入即时通信 IM 大家庭！查看详情>>\",\"url\": \"https://cloud.tencent.com/product/im\"}";
        MessageInfo info = MessageInfoUtil.buildCustomMessage(str);
        mChatLayout.sendMessage(info, false);
    }
}