package com.tencent.tecentim.messagehelper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.tencent.imsdk.v2.V2TIMCustomElem;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.input.InputLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.inputmore.InputMoreActionUnit;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ICustomMessageViewGroup;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.IOnCustomMessageDrawListener;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.tecentim.R;
import com.tencent.tecentim.messagehelper.custommessage.CustomCardMessage;
import com.tencent.tecentim.messagehelper.custommessage.CustomCardMessageTIMUIController;
import com.tencent.tecentim.order.ProductOrderActivity;

public class ChatLayoutHelper {

    private static final String TAG = ChatLayoutHelper.class.getSimpleName();

    private Context mContext;

    public ChatLayoutHelper(Context context) {
        mContext = context;
    }

    public void customizeChatLayout(final ChatLayout layout) {
        MessageLayout messageLayout = layout.getMessageLayout();
        // 设置自定义的消息渲染时的回调
        messageLayout.setOnCustomMessageDrawListener(new CustomMessageDraw());


        //====== InputLayout使用范例 ======//
        final InputLayout inputLayout = layout.getInputLayout();
        inputLayout.enableAudioCall();
        inputLayout.enableVideoCall();

        // TODO 可以自己增加一些功能，可以打开下面代码测试
        // 增加一个欢迎提示富文本
        InputMoreActionUnit unit = new InputMoreActionUnit();
        unit.setIconResId(R.drawable.custom);
        unit.setTitleId(R.string.test_custom_action);
        unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Gson gson = new Gson();
                CustomHelloMessage customHelloMessage = new CustomHelloMessage();
                customHelloMessage.version = TUIKitConstants.version;
                customHelloMessage.combination_to_left_righht = MyApplication.instance().getString(R.string.welcome_tip);
                customHelloMessage.link = "https://cloud.tencent.com/document/product/269/3794";

                String data = gson.toJson(customHelloMessage);
                MessageInfo info = MessageInfoUtil.buildCustomMessage(data);
                layout.sendMessage(info, false);*/

                mContext.startActivity(new Intent(mContext, ProductOrderActivity.class));

            }
        });
        inputLayout.addAction(unit);
    }


    public class CustomMessageDraw implements IOnCustomMessageDrawListener {

        /**
         * 自定义消息渲染时，会调用该方法，本方法实现了自定义消息的创建，以及交互逻辑
         *
         * @param parent 自定义消息显示的父View，需要把创建的自定义消息view添加到parent里
         * @param info   消息的具体信息
         */
        @Override
        public void onDraw(ICustomMessageViewGroup parent, MessageInfo info) {
            // 获取到自定义消息的json数据
            if (info.getTimMessage().getElemType() != V2TIMMessage.V2TIM_ELEM_TYPE_CUSTOM) {
                return;
            }
            V2TIMCustomElem elem = info.getTimMessage().getCustomElem();
            // 自定义的json数据，需要解析成bean实例
            //CustomHelloMessage data = null;
            CustomCardMessage data = null;
            try {
                //data = new Gson().fromJson(new String(elem.getData()), CustomHelloMessage.class);
                data = new Gson().fromJson(new String(elem.getData()), CustomCardMessage.class);
            } catch (Exception e) {
                Log.w(TAG, "bright8#invalid json: " + new String(elem.getData()) + " " + e.getMessage());
            }
            /*if (data == null) {
                Log.e(TAG, "bright8#No Custom Data: " + new String(elem.getData()));
            } else if (data.version == TUIKitConstants.JSON_VERSION_1
                    || (data.version == TUIKitConstants.JSON_VERSION_4 && data.businessID.equals("text_link"))) {
                CustomHelloMessageTIMUIController.onDraw(parent, data);
            } else {
                Log.w(TAG, "bright8#unsupported version: " + data);
            }*/

            if(data == null) {
                Log.e(TAG, "bright8#No Custom Data: " + new String(elem.getData()));
            } else {
                CustomCardMessageTIMUIController.onDraw(parent, data);
            }
        }
    }
}
