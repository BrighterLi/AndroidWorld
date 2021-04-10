package com.tencent.tecentim.messagehelper.custommessage;

import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.tecentim.R;
import com.tencent.tecentim.messagehelper.MyApplication;

public class CustomHelloMessage {
    public String businessID = TUIKitConstants.BUSINESS_ID_CUSTOM_HELLO;
    public String text = MyApplication.instance().getString(R.string.welcome_tip);
    public String link = "https://cloud.tencent.com/document/product/269/3794";

    public int version = TUIKitConstants.JSON_VERSION_UNKNOWN;
}
