package com.xiaoming.net;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//网络检测
public class NetDetectActivity extends AppCompatActivity {
    private static final String TAG = "NetDetectActivity";
    private Button mBtnNetDetect;
    private TextView mTvDnsAddress;
    private String outIp2;
    private StringBuilder netInfoStrBuilder = new StringBuilder();

    private String[] platforms = {
            "http://pv.sohu.com/cityjson",
            "http://pv.sohu.com/cityjson?ie=utf-8",
            "http://ip.chinaz.com/getip.aspx"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_detect);

        initView();
    }

    private void initView() {
        mBtnNetDetect = findViewById(R.id.bt_net_detect);
        mTvDnsAddress = findViewById(R.id.tv_dns_address);

        mBtnNetDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dnsAddress = getDnsAddress();
                String outIp = getOutNetIp(NetDetectActivity.this, 0);
                Log.d(TAG, "bright#dns地址：" + dnsAddress + "\n" +  "公网出口IP:" + outIp);
                netInfoStrBuilder.append("dns地址：" + dnsAddress + "\n" +  "公网出口IP:" + outIp);
                mTvDnsAddress.setText(netInfoStrBuilder.toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        outIp2 =  getOutNetIp2();
                        netInfoStrBuilder.append("\n" + "公网出口Ip2:" + outIp2 + "\n");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "bright#公网出口Ip2:" + outIp2);
                                mTvDnsAddress.setText(netInfoStrBuilder.toString());
                            }
                        });
                    }
                }).start();
            }
        });
    }

    //https://www.cnblogs.com/alex-zhao/p/5254624.html
    private String getDnsAddress() {
        Process cmdProcess = null;
        BufferedReader reader = null;
        String dnsAddress = "";
        try {
            cmdProcess = Runtime.getRuntime().exec("getprop net.dns1");
            reader = new BufferedReader(new InputStreamReader(cmdProcess.getInputStream()));
            dnsAddress = reader.readLine();
            Log.e(TAG,"bright#dnsAddress:" + dnsAddress);
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

    //公网出口IP 获取到的总是局域网的Ip
    //https://www.jianshu.com/p/1e3eaf887191
    private String getOutNetIp(Context context, int index) {
        if (index < platforms.length) {
            BufferedReader reader = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = null;
                url = new URL(platforms[index]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(5000);
                urlConnection.setConnectTimeout(5000);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { //找到服务器的情况下,可能还会找到别的网站返回html格式的数据
                    InputStream inputStream = urlConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));//注意编码，会出现乱码
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    reader.close();//内部会关闭 InputStream
                    urlConnection.disconnect();

                    if(index == 0 || index == 1) {
                        //截取字符串
                        int startIndex = stringBuilder.indexOf("{");
                        int endIndex = stringBuilder.indexOf("}");
                        String json = stringBuilder.substring(startIndex, endIndex+1);
                        JSONObject jsonObject = new JSONObject(json);
                        String ip = jsonObject.getString("cip");
                        return ip;
                    } else if(index == 2) {
                        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                        return jsonObject.getString("ip");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG,"bright#localNetIp:" + getLocalNetIp(context));
            return getLocalNetIp(context);
        }
        return getOutNetIp(context, ++index);
    }

    private String getLocalNetIp(Context context) {
        WifiManager wifiManager = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE));
        if(!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    //这段是转换成点分式IP的码
    private static String intToIp(int ip) {
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) &     0xFF) + "." + (ip >> 24 & 0xFF);
    }


    //https://www.cnblogs.com/feijian/p/6601427.html
    /**
     * 获取外网的IP(要访问Url，要放到后台线程里处理)
     *
     * @param @return
     * @return String
     * @throws
     * @Title: GetNetIp
     * @Description:
     */
    public static String getOutNetIp2() {
        URL infoUrl = null;
        InputStream inStream = null;
        String ipLine = "";
        HttpURLConnection httpConnection = null;
        try {
//            infoUrl = new URL("http://ip168.com/");
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
        Log.e(TAG,"bright#getOutNetIp2:" + ipLine);
        return ipLine;
    }
    //UID
    //操作系统

    //域名解析
    //https://blog.csdn.net/u013072976/article/details/79074687
    private String[] getIPAddressFromDomainName() {
        return null;
    }
    //ping
}
