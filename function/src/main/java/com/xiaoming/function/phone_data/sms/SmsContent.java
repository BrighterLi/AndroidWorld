package com.xiaoming.function.phone_data.sms;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SmsContent {

    private static volatile int sTotalCount = 0;

    public static JSONObject getFormatJson(@NonNull Context context, Uri uri,
                                           boolean isNeedMatchSpecialJson,
                                           @Nullable List<String> specialList, long startTime, int maxCount) {

        JSONObject rootObj = new JSONObject();
        JSONArray array = new JSONArray();
        Cursor cursor = null;

        boolean isUploadCompleted = true;

        if (maxCount <= 0) {
            return rootObj;
        }
        if (isNeedMatchSpecialJson) {
            if (specialList == null || specialList.size() == 0) {
                return rootObj;
            }
        }
       /* if (!AntiPermission.checkReadSMS(context)) {
            return rootObj;
        }*/
        try {
            if (uri == null) {
                uri = SmsUriType.SMS_URI_ALL;
            }
            String[] projection = new String[]{"address", "date", "body", "type"};
            cursor = context.getContentResolver().query(uri, projection, null, null, "date desc");
            if (cursor == null) {
                return rootObj;
            }
            sTotalCount = cursor.getCount();
            int dateColumn = cursor.getColumnIndex("date");
            int smsBodyColumn = cursor.getColumnIndex("body");
            int phoneNumberColumn = cursor.getColumnIndex("address");
            int typeColumn = cursor.getColumnIndex("type");//type 短信类型 1：inbox  2：sent 3：draft56  4：outbox  5：failed  6：queued
            int i = 0;
            while (cursor.moveToNext()) {
                if (i >= maxCount) {
                    isUploadCompleted = false;
                    break;
                } else {
                    isUploadCompleted = true;
                }
                long date = cursor.getLong(dateColumn);
                if (date < startTime) {//已超出了限定时间内
                    break;
                }
                String body = cursor.getString(smsBodyColumn);
                if (TextUtils.isEmpty(body)) {
                    continue;
                }
                if (isNeedMatchSpecialJson) {
                    for (int j = 0; j < specialList.size(); j++) {
                        String str = specialList.get(j);
                        if (!TextUtils.isEmpty(str) && body.contains(str)) {//包含特定字符
                            String phoneNum = cursor.getString(phoneNumberColumn);
                            //私人号码内容不上传
                            if (!TextUtils.isEmpty(phoneNum) && !StringUtil.isPhone(phoneNum)) {
                                JSONObject object = new JSONObject();
                                object.put("opposite_tel", phoneNum);
                                object.put("sms_time", date / 1000L);
                                object.put("sms_body", body);
                                object.put("source", cursor.getInt(typeColumn) + "");
                                array.put(object);
                                i++;
                            }
                            break;
                        }
                    }
                } else {
                    String phoneNum = cursor.getString(phoneNumberColumn);
                    //私人号码内容不上传
                    if (!TextUtils.isEmpty(phoneNum) && !StringUtil.isPhone(phoneNum)) {
                        JSONObject object = new JSONObject();
                        object.put("opposite_tel", phoneNum);
                        object.put("sms_time", date / 1000L);
                        object.put("sms_body", body);
                        object.put("source", cursor.getInt(typeColumn) + "");
                        array.put(object);
                        i++;
                    }
                }
            }

            long uploadTimeLowerLimit = startTime / 1000L;//上传时间下限
            long uploadTimeUpperLimit = System.currentTimeMillis() / 1000L;//上传时间上限

            rootObj.put("is_upload_completed", isUploadCompleted ? 1 : 0);
            rootObj.put("upload_time_lower_limit", uploadTimeLowerLimit);
            rootObj.put("upload_time_upper_limit", uploadTimeUpperLimit);
            rootObj.put("sms_content", array);
        } catch (Exception e) {

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return rootObj;
    }

    public static long getTotalCount(@NonNull Context context, Uri uri) {
        if (sTotalCount > 0) {
            return sTotalCount;
        }

        Cursor cursor = null;
        try {
            if (uri == null) {
                uri = SmsUriType.SMS_URI_ALL;
            }
            String[] projection = new String[]{"address", "date", "body", "type"};
            cursor = context.getContentResolver().query(uri, projection, null, null, "date desc");
            if (cursor != null) {
                sTotalCount = cursor.getCount();
            }
        } catch (Throwable e) {

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return sTotalCount;
    }
}
