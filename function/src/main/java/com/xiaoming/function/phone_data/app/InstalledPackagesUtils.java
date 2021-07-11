package com.xiaoming.function.phone_data.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.ArrayMap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

//获取安装包
public class InstalledPackagesUtils {
    private static JSONArray sAppInfoList;

    public static synchronized JSONArray getInstalledAppJson(Context context) {
        sAppInfoList = new JSONArray();
        PackageManager pm = context.getPackageManager();

        if (pm != null) {
            List<PackageInfo> packages = null;
            try {
                packages = pm.getInstalledPackages(0);
            } catch (Throwable ignore) {
            }
            if (packages != null && packages.size() > 0) {
                for (PackageInfo packageInfo : packages) {
                    if (packageInfo == null || packageInfo.applicationInfo == null) {
                        continue;
                    }
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                        try {
                            String appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                            JSONObject object = new JSONObject();
                            object.put("name", appName);
                            object.put("version", packageInfo.versionName);
                            object.put("package_name", packageInfo.packageName);
                            object.put("install_time", packageInfo.firstInstallTime);
                            object.put("update_time", packageInfo.lastUpdateTime);
                            sAppInfoList.put(object);
                        } catch (Throwable e) {
                        }
                    }
                }
            }
        }
        return sAppInfoList;
    }
}
