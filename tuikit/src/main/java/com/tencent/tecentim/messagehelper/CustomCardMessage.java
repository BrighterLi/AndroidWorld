package com.tencent.tecentim.messagehelper;

import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.tecentim.R;

public class CustomCardMessage {
    String businessID = TUIKitConstants.BUSINESS_ID_CUSTOM_HELLO;
    String text = MyApplication.instance().getString(R.string.welcome_tip);
    String link = "https://cloud.tencent.com/document/product/269/3794";

    int version = TUIKitConstants.JSON_VERSION_UNKNOWN;
}
