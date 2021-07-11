package com.xiaoming.function.phone_data.sms;

import android.content.Context;

import org.json.JSONObject;

//获取短信
public class SmsUtils {

    public static JSONObject addSmsInfoList(Context context){
        long start = 0;
        int max = 1000;
        JSONObject smsInfo = null;

        smsInfo = SmsContent.getFormatJson(context,
                null, false, null, start, max);
        return smsInfo;
    }
}
