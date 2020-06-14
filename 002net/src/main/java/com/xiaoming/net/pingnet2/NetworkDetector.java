package com.xiaoming.net.pingnet2;

import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.xiaoming.net.pingnet2.bean.PingNetworkBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkDetector {
    private static final String TAG = "NetworkDetector";
    private static Map<String, Object> infoMap = new HashMap<String, Object>();
    private static List<Map> infoList = new ArrayList<>();
    private static List<String> sPingHostList = new ArrayList<String>(Arrays.asList("www.baidu.com", "cloud.tencent.com", "aliyun.com", "fm.fenqile.com", "m.fenqile.com",
            "pay.m.fenqile.com", "repay.m.fenqile.com", "loan.m.fenqile.com", "cc.m.fenqile.com", "auth.fenqile.com", "cres.fenqile.com"));

    public static void startDetectAndReport() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                infoMap.put("uid", "1111");
                infoMap.put("version", "5.9.0");
                infoMap.put("os", Build.VERSION.RELEASE);
                infoMap.put("operator", "联通");
                //公网出口IP
                String outIp = NetworkIpUtil.getOutNetIp();
                infoMap.put("ip", outIp);
                //Dns,wifi情况下会是局域网IP
                String dns = NetworkIpUtil.getDnsAddress();
                infoMap.put("dns", dns);
                Log.i(TAG, "\n" + "uid：" + "111" + "\n" +
                        "version：" + "7.0.0" + "\n" +
                        "os：" + Build.VERSION.RELEASE + "\n" +
                        "ip：" + outIp + "\n" +
                        "dns：" + dns + "\n");
                infoList = pingHostList();
                //infoMap.put("host_list", infoList);
                reportDetectInfo();
            }
        }).start();
    }

    public static List<Map> pingHostList() {
        List<Map> pingList = new ArrayList<>();
        for (int i = 0; i < sPingHostList.size(); i++) {
            Map pingMap = new ArrayMap();
            PingNetworkBean pingNetworkBean = new PingNetworkBean(sPingHostList.get(i), 3, 5);
            pingNetworkBean = PingNetworkUtil.ping(pingNetworkBean);
            pingMap.put("success", pingNetworkBean.getSuccess());
            pingMap.put("host", pingNetworkBean.getHost());
            pingMap.put("ip", pingNetworkBean.getParsedIp());
            pingMap.put("time", pingNetworkBean.getPingTime());
            pingList.add(pingMap);
            Log.i(TAG, "bright" + "\n" + "success：" + pingNetworkBean.getSuccess() + "\n" +
                    "host：" + pingNetworkBean.getHost() + "\n" +
                    "ip：" + pingNetworkBean.getParsedIp() + "\n" +
                    "time：" + pingNetworkBean.getPingTime() + "\n");
        }
        return pingList;
    }

    private static void reportDetectInfo() {
        JSONObject jsonObject = new JSONObject();
        for (String key : infoMap.keySet()) {
            try {
                jsonObject.put(key, infoMap.get(key));
                JSONArray jsonArray = new JSONArray(infoList);
                jsonObject.put("host_list", jsonArray);
            } catch (JSONException e) {
                Log.i(TAG, "bright9#e：" + e);
                e.printStackTrace();
            }
        }
        Log.i(TAG, "bright9#jsonObject：" + jsonObject);
    }
}
