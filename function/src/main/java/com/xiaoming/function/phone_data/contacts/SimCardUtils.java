package com.xiaoming.function.phone_data.contacts;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import androidx.core.app.ActivityCompat;

/**
 * sim联系人获取类
 */
public class SimCardUtils {

    private static final Uri SIM_SINGLE_URI = Uri.parse("content://icc/adn");
    private static final Uri SIM_MULTI_URI_0 = Uri.parse("content://icc/adn/subId/0");
    private static final Uri SIM_MULTI_URI_1 = Uri.parse("content://icc/adn/subId/1");
    private static final Uri SIM_MULTI_URI_2 = Uri.parse("content://icc/adn/subId/2");

    private static int sTotalCount;

    public static Map<String, List<String>> queryAllSimContacts(Context context, int max, Map<String, List<String>> map) {
        // 先查单卡
        if (map == null) {
            map = new HashMap<>(10);
        }
        if (!hasSimPermission(context)) {
            return map;
        }
        querySimContacts(context, SimCardUtils.SIM_SINGLE_URI, map, max);
        // 再查多卡 5.1之后才支持
        if (Build.VERSION.SDK_INT >= 22) {
            querySimContacts(context, SimCardUtils.SIM_MULTI_URI_0, map, max);
            querySimContacts(context, SimCardUtils.SIM_MULTI_URI_1, map, max);
            querySimContacts(context, SimCardUtils.SIM_MULTI_URI_2, map, max);
        }
        return map;
    }


    @SuppressWarnings("all")
    private static Map<String, List<String>> querySimContacts(Context context, Uri uri,
                                                              Map<String, List<String>> map, int max) {
        if (map == null) {
            map = new HashMap<>(10);
        }
        if (map.size() >= max) {
            return map;
        }
        Cursor cursor = null;
        try {
            context = context.getApplicationContext();
            try {
                cursor = context.getContentResolver().query(uri, null, null, null, null);
            } catch (Throwable e) {

            }
            if (cursor == null) {
                return map;
            }
            // 兼容 oppo 5.1机型数据库字段错位
            int nameIndex = 0, numberIndex = 1;
            int nameColumn = cursor.getColumnIndex("name");
            if (nameColumn >= 0) {
                nameIndex = nameColumn;
            }
            int numberColumn = cursor.getColumnIndex("number");
            if (numberColumn >= 0) {
                numberIndex = numberColumn;
            }

            if (nameIndex == numberIndex) {
                nameIndex = 0;
                numberIndex = 1;
            }

            while (map.size() < max && cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                String mobile = cursor.getString(numberIndex);

                if (name == null) {
                    name = "";
                }
                if (mobile != null) {
                    mobile = mobile.replace(" ", "");
                }
                if (!TextUtils.isEmpty(mobile)) {
                    List<String> list = map.get(name);
                    if (list != null) {
                        // 去重
                        if (!list.contains(mobile)) {
                            list.add(mobile);
                        }
                    } else {
                        list = new LinkedList<>();
                        list.add(mobile);
                        map.put(name, list);
                    }
                }
            }
        } catch (Throwable e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return map;
    }


    private static boolean hasSimPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED;
    }


    public static int getTotalContactsCount(Context context) {
        if (sTotalCount > 0) {
            return sTotalCount;
        }
        if (!hasSimPermission(context)) {
            return 0;
        }
        int count = getContactsCount(context, SimCardUtils.SIM_SINGLE_URI);
        if (Build.VERSION.SDK_INT >= 22) {
            count += getContactsCount(context, SimCardUtils.SIM_MULTI_URI_0);
            count += getContactsCount(context, SimCardUtils.SIM_MULTI_URI_1);
            count += getContactsCount(context, SimCardUtils.SIM_MULTI_URI_2);
        }
        if (count > 0) {
            sTotalCount = count;
        }
        return count;
    }

    private static int getContactsCount(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            context = context.getApplicationContext();
            try {
                cursor = context.getContentResolver().query(uri, null, null, null, null);
            } catch (Throwable e) {
            }
            if (cursor != null) {
                return cursor.getCount();
            }
        } catch (Throwable e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return 0;
    }


}
