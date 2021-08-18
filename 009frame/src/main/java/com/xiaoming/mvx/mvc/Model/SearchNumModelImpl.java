package com.xiaoming.mvx.mvc.Model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.xiaoming.mvx.mvc.SearchNumListener;

public class SearchNumModelImpl implements SearchNumInterface {
    private Context mContext;

    public SearchNumModelImpl(Context context) {
        mContext = context;
    }

    //Model:通过名字查询号码，返回给View显示
    @Override
    public void getNum(String name, SearchNumListener listener) {
        if(isEmptyString(name) || isEmptyString(searchNumber(name))) {
            listener.onError();
        } else {
            listener.onSuccess(searchNumber(name));
        }
    }

    public String searchNumber(String name) {
        Cursor cursor = mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            if(name.equals(contactName)) {
                Cursor phone = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                if(phone.moveToNext()) {
                    String phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    return phoneNumber;
                }
            }
        }
        return null;
    }

    public static boolean isEmptyString(String str) {
        return str == null || str.trim().length() == 0;
    }
}
