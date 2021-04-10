package com.tencent.tecentim.messagehelper;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ICustomMessageViewGroup;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.tencent.tecentim.R;

public class CustomCardMessageTIMUIController {

    private static final String TAG = CustomCardMessageTIMUIController.class.getSimpleName();

    public static void onDraw(ICustomMessageViewGroup parent, final CustomCardMessage data) {

        // 把自定义消息view添加到TUIKit内部的父容器里
        View view = LayoutInflater.from(MyApplication.instance()).inflate(R.layout.test_custom_message_layout1, null, false);
        parent.addMessageContentView(view);

        // 自定义消息view的实现，这里仅仅展示文本信息，并且实现超链接跳转
        TextView textView = view.findViewById(R.id.test_custom_message_tv);
        final String text = MyApplication.instance().getString(R.string.no_support_msg);
        if (data == null) {
            textView.setText(text);
        } else {
            textView.setText(data.text);
        }
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data == null) {
                    Log.e(TAG, "bright8#Do what?");
                    ToastUtil.toastShortMessage(text);
                    return;
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(data.link);
                intent.setData(content_url);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.instance().startActivity(intent);
            }
        });
    }
}
