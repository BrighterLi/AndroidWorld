package com.xiaoming.net.packegenet;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * App故障诊断
 * */
public class AppFaultDiagnosisUtils {
    private static String sPingDelayTime = "-1";
    private static Map<String, Object> sNetInfoMap = new HashMap<String, Object>();
    private static List<String> sPingDnList = new ArrayList<String>(Arrays.asList("www.baidu.com","cloud.tencent.com","aliyun.com"));
    private static List<String> sDnResolveList = new ArrayList<String>(Arrays.asList( "cres.fenle.com"));


    public static Map getNetInfos() {
        //公网出口IP
        String outIp = getOutNetIp();
        sNetInfoMap.put("out_ip", outIp);
        //Dns,wifi情况下会是局域网IP
        String dns = getDnsAddress();
        sNetInfoMap.put("dns", dns);
        //ping一列host
        getPingDnResultForHostList();
        //解析一列host
        getResolveDnResultForHostList();
        return sNetInfoMap;
    }
    //获取外网Ip
    public static String getOutNetIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        String ipLine = "";
        HttpURLConnection httpConnection = null;
        try {
            infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
            URLConnection connection = infoUrl.openConnection();
            httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null){
                    strber.append(line + "\n");
                }
                Pattern pattern = Pattern
                        .compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
                Matcher matcher = pattern.matcher(strber.toString());
                if (matcher.find()) {
                    ipLine = matcher.group();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
                httpConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return ipLine;
    }

    //ping一列host
    public static Map getPingDnResultForHostList() {
        for(int i=0;i<sPingDnList.size();i++) {
            String host = sPingDnList.get(i);
            boolean isPing = pingDn(host);
            if (isPing) {
                sNetInfoMap.put(host, sPingDelayTime);
            } else {
                sNetInfoMap.put(host, "-1");
            }
        }
        return sNetInfoMap;
    }

    //解析一列host
    public static Map getResolveDnResultForHostList() {
        for (int i=0;i<sDnResolveList.size();i++) {
            String host = sDnResolveList.get(i);
            String ip = getIpByDn(host);
            sNetInfoMap.put(host, ip);
        }
        return sNetInfoMap;
    }

    //ping域名
    private static boolean pingDn(String host) {
        if(TextUtils.isEmpty(host)) {
            return false;
        }
        String dn = host;
        try {
            Process process = Runtime.getRuntime().exec("ping -c 3 -w 100 " + dn); //ping 3次
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str = new String();
            while ((str = bufferedReader.readLine()) != null){
                if(str.contains("avg")) {
                    int i = str.indexOf("/", 20);
                    int j = str.indexOf(".", i);
                    sPingDelayTime = str.substring(i+1, j);
                }
            }
            //ping状态
            int status = process.waitFor();
            if(status == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    //获取dns
    private static String getDnsAddress() {
        Process cmdProcess = null;
        BufferedReader reader = null;
        String dnsAddress = "";
        try {
            cmdProcess = Runtime.getRuntime().exec("getprop net.dns1");
            reader = new BufferedReader(new InputStreamReader(cmdProcess.getInputStream()));
            dnsAddress = reader.readLine();
            return dnsAddress;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cmdProcess.destroy();
        }
    }

    //域名解析
    private static String getIpByDn(String host) {
        String ipAddress = null;
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            ipAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ipAddress;
    }

}

