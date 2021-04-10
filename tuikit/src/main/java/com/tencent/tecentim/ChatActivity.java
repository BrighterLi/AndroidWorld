package com.tencent.tecentim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.tecentim.messagehelper.ChatLayoutHelper;
import com.tencent.tecentim.messagehelper.custommessage.CustomCardMessage;
import com.tencent.tecentim.messagehelper.custommessage.CustomHelloMessage;
import com.tencent.tecentim.messagehelper.MyApplication;
import com.tencent.tecentim.view.card.RoundTextView;

public class ChatActivity extends AppCompatActivity {
    private ChatLayout mChatLayout;
    private ChatInfo mChatInfo;
    private TitleBarLayout mTitleBar;
    private String userId;

    private View mCardView;
    private TextView mCardPriceTv; //卡片售价
    private ImageView mCardCloseIv;
    private RoundTextView mCardSendMessage;

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

        mCardView = mChatLayout.getCardView();
        mCardPriceTv = mChatLayout.getCardPriceTv();
        mCardPriceTv.setText("500");
        mCardCloseIv = mChatLayout.getCardCloseIv();
        mCardSendMessage = mChatLayout.getCardSendMessageBt();
        if(mCardCloseIv != null) {
            mCardCloseIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( mCardView != null) {
                        mCardView.setVisibility(View.GONE);
                    }
                }
            });
        }
        if(mCardSendMessage != null) {
            mCardSendMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gson gson = new Gson();
                    CustomCardMessage customCardMessage = new CustomCardMessage();
                    customCardMessage.imgUrl = "";
                    customCardMessage.monthPay = "20.31";
                    customCardMessage.title = "这是一个非常漂亮的手机";
                    customCardMessage.salePrice = "700";
                    String data = gson.toJson(customCardMessage);
                    MessageInfo info = MessageInfoUtil.buildCustomMessage(data);
                    //mChatLayout.getMessageLayout().setRightBubble(new ColorDrawable(0xFFFFFFFF));
                    mChatLayout.sendMessage(info, false);
                    mCardView.setVisibility(View.GONE);
                }
            });
        }

    }

}