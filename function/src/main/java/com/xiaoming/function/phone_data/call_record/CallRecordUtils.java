package com.xiaoming.function.phone_data.call_record;

import android.content.Context;

import org.json.JSONObject;

//获取通话记录
public class CallRecordUtils {

    public static JSONObject getCallRecordsInfo(Context context, int start, int max) {
        JSONObject callRecordInfo = null;
        callRecordInfo = CallRecordContent.getFormatJson(context, start, max);
        return callRecordInfo;
    }
}
