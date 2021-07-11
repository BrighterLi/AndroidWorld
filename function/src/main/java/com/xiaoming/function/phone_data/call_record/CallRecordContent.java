package com.xiaoming.function.phone_data.call_record;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.annotation.NonNull;

public class CallRecordContent {

        private static volatile int sTotalCount = 0;

        @SuppressLint("all")
        public static JSONObject getFormatJson(@NonNull Context context, long start, int count) {
            JSONObject rootObj = new JSONObject();
            Cursor cursor = null;

            boolean isUploadCompleted = true;

            if (count <= 0) {
                return rootObj;
            }

            try {
                String[] projection = new String[]{
                        CallLog.Calls.NUMBER, CallLog.Calls.DATE,
                        CallLog.Calls.DURATION, CallLog.Calls.TYPE};

                cursor = context.getContentResolver().query(
                        CallLog.Calls.CONTENT_URI, projection, null, null,
                        CallLog.Calls.DATE + " desc");

                if (cursor == null) {
                    return rootObj;
                }
                JSONArray array = new JSONArray();
                int numberColumn = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                int dateColumn = cursor.getColumnIndex(CallLog.Calls.DATE);
                int durationColumn = cursor.getColumnIndex(CallLog.Calls.DURATION);
                int typeColumn = cursor.getColumnIndex(CallLog.Calls.TYPE);

                int i = 0;
                while (cursor.moveToNext()) {
                    if (i >= count) {
                        isUploadCompleted = false;
                        break;
                    } else {
                        isUploadCompleted = true;
                    }
                    long date = cursor.getLong(dateColumn);
                    if (date < start) {
                        break;
                    }
                    JSONObject object = new JSONObject();
                    object.put("opposite_tel", cursor.getString(numberColumn));
                    object.put("call_start_time", date / 1000L + "");
                    object.put("call_end_time", date / 1000L + cursor.getInt(durationColumn) + "");
                    object.put("duration", cursor.getInt(durationColumn) + "");
                    object.put("source", cursor.getInt(typeColumn) + "");
                    array.put(object);
                    i++;
                }

                long uploadTimeLowerLimit = start / 1000L;//上传时间下限
                long uploadTimeUpperLimit = System.currentTimeMillis() / 1000L;//上传时间上限

                rootObj.put("is_upload_completed", isUploadCompleted ? 1 : 0);
                rootObj.put("upload_time_lower_limit", uploadTimeLowerLimit);
                rootObj.put("upload_time_upper_limit", uploadTimeUpperLimit);
                rootObj.put("call_records_content", array);
            } catch (Exception e) {
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            return rootObj;
        }

        @SuppressLint("MissingPermission")
        public static int getTotalCount(@NonNull Context context) {
            if (sTotalCount > 0) {
                return sTotalCount;
            }

            Cursor cursor = null;
            try {
                String[] projection = new String[]{
                        CallLog.Calls.NUMBER, CallLog.Calls.DATE,
                        CallLog.Calls.DURATION, CallLog.Calls.TYPE};

                cursor = context.getContentResolver().query(
                        CallLog.Calls.CONTENT_URI, projection, null, null,
                        CallLog.Calls.DATE + " desc");

                if (cursor != null) {
                    sTotalCount = cursor.getCount();
                }
            } catch (Exception e) {

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            return sTotalCount;
        }
}
