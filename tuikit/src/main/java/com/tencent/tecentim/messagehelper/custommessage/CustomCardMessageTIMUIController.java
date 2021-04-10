package com.tencent.tecentim.messagehelper.custommessage;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ICustomMessageViewGroup;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.tencent.tecentim.R;
import com.tencent.tecentim.messagehelper.MyApplication;

public class CustomCardMessageTIMUIController {

    private static final String TAG = CustomHelloMessageTIMUIController.class.getSimpleName();

    public static void onDraw(ICustomMessageViewGroup parent, final CustomCardMessage data) {

        // 把自定义消息view添加到TUIKit内部的父容器里
        View view = LayoutInflater.from(MyApplication.instance()).inflate(R.layout.custom_card_message_layout, null, false);
        parent.addMessageContentView(view);

        TextView cardPrice = view.findViewById(R.id.tv_card_price);
        cardPrice.setText(data.salePrice);

    }
}
