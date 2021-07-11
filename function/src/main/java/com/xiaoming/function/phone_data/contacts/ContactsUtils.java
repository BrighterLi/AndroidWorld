package com.xiaoming.function.phone_data.contacts;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//获取通讯录
public class ContactsUtils {
    private static volatile int sTotalCount = 0;

    /**
     * @param context 上下文
     * @param max     联系人个数限制
     * @param withSim 是否包含sim卡联系人
     * @return 联系人的 JSONArray
     */
    public static JSONArray getContactsCompat(Context context, int max, boolean withSim) {
        Map<String, List<String>> contacts = getBookContacts(context, max, null);
        if (withSim) {
            contacts = getSimContacts(context, max, contacts);
        }
        if (withSim && contacts != null) {
            sTotalCount = contacts.size();
        }
        return convertContactsToJson(null, contacts);
    }

    public static Map<String, List<String>> getSimContacts(Context context, int max, Map<String, List<String>> map) {
        return SimCardUtils.queryAllSimContacts(context, max, map);
    }


    public static Map<String, List<String>> getBookContacts(Context context, int max, Map<String, List<String>> contactsMap) {
        if (contactsMap == null) {
            contactsMap = new HashMap<>(10);
        }
        if (contactsMap.size() >= max) {
            return contactsMap;
        }
        Cursor rootCursor = null;
        Cursor phonesCursor = null;
        try {
            rootCursor = context.getContentResolver().query(
                    ContactsContract.Contacts.CONTENT_URI,
                    null,
                    null,
                    null,
                    ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");

            if (rootCursor != null && rootCursor.moveToFirst()) {
                int idColumn = rootCursor.getColumnIndex(ContactsContract.Contacts._ID);
                int displayNameColumn = rootCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                boolean isAlreadySet = false;
                do {
                    String contactId = rootCursor.getString(idColumn);
                    String disPlayName = rootCursor.getString(displayNameColumn);
                    if (contactId == null) {
                        continue;
                    }
                    if (disPlayName == null) {
                        disPlayName = "";
                    }


                    // 没有手机号码
                    int phoneCount = rootCursor.getInt(rootCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    if (phoneCount <= 0) {
                        continue;
                    }

                    phonesCursor = context.getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                    + " = " + contactId, null, null);

                    if (phonesCursor != null) {
                        if (phonesCursor.moveToFirst()) {
                            boolean isInMap;
                            List<String> mobileList = contactsMap.get(disPlayName);
                            if (mobileList == null) {
                                mobileList = new LinkedList<>();
                                isInMap = false;
                            } else {
                                isInMap = true;
                            }
                            int numberIndex = phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                            do {
                                String phoneNumber = phonesCursor.getString(numberIndex);
                                if (phoneNumber != null) {
                                    phoneNumber = phoneNumber.replace(" ", "");
                                }
                                if (!TextUtils.isEmpty(phoneNumber) && !mobileList.contains(phoneNumber)) {
                                    // 4.4.0新增逻辑 名称和号码一样的不要
                                    if (!phoneNumber.equals(disPlayName)) {
                                        mobileList.add(phoneNumber);
                                    }
                                }
                            } while (phonesCursor.moveToNext());

                            if (!isInMap && mobileList.size() > 0) {
                                contactsMap.put(disPlayName, mobileList);
                            }
                        }
                        phonesCursor.close();
                        phonesCursor = null;
                    }

                } while (contactsMap.size() < max && rootCursor.moveToNext());
            }
        } catch (Throwable e) {
        } finally {
            if (phonesCursor != null) {
                phonesCursor.close();
            }
            if (rootCursor != null) {
                rootCursor.close();
            }
        }

        return contactsMap;
    }


    private static JSONArray convertContactsToJson(JSONArray arr, Map<String, List<String>> map) {
        if (arr == null) {
            arr = new JSONArray();
        }
        if (map != null && map.size() > 0) {
            Set<String> keySet = map.keySet();
            for (String name : keySet) {
                List<String> list = map.get(name);
                if (list != null && list.size() > 0) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        JSONArray jsonArray = new JSONArray();
                        for (String mobile : list) {
                            jsonArray.put(mobile);
                        }
                        jsonObject.put("name", name);
                        jsonObject.put("mobile", jsonArray);
                        arr.put(jsonObject);
                    } catch (JSONException e) {
                    }
                }
            }
        }
        return arr;
    }
}
