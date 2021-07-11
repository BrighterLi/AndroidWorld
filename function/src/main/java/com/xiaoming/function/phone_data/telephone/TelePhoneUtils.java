package com.xiaoming.function.phone_data.telephone;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

import androidx.core.content.ContextCompat;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

//获取手机号码
public class TelePhoneUtils {

    @SuppressLint("MissingPermission")
    public static String getPhoneNum(Context context) {

        TelephonyManager tm = getTelephonyManager(context);
        if (tm != null) {
            return tm.getLine1Number();
        }
        return "";
    }

    public static TelephonyManager getTelephonyManager(Context context) {
        boolean permission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                == PERMISSION_GRANTED;
        return permission ? (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE) : null;
    }
}
