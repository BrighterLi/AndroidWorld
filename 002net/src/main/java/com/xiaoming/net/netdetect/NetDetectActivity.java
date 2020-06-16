package com.xiaoming.net.netdetect;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaoming.net.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xiaoming.net.netdetect.DnsUtil.getDnsFromCommand;
import static com.xiaoming.net.netdetect.DnsUtil.getDnsFromConnectionManager;


//网络检测
public class NetDetectActivity extends AppCompatActivity {
    private static final String TAG = "NetDetectActivity";
    private Button mBtnNetDetect;
    private TextView mTvNetInfo;
    private EditText mEtInputDn;
    private String outIp;
    private String[] parsedIpArray;
    private StringBuilder netInfoStrBuilder;
    private boolean isPingGood;
    private String netDelayTime = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_detect);

        initView();
    }

    private void initView() {
        mBtnNetDetect = findViewById(R.id.bt_net_detect);
        mTvNetInfo = findViewById(R.id.tv_dns_address);
        mEtInputDn = findViewById(R.id.et_domain_name);

        mBtnNetDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                netInfoStrBuilder = new StringBuilder();
                //dns
                String dnsAddress = getDnsAddress();
                Log.d(TAG, "bright#dns地址：" + dnsAddress);
                netInfoStrBuilder.append("dns地址：" + dnsAddress);
                //dns2
                String dns2 = DnsUtil.getDns2(NetDetectActivity.this);
                netInfoStrBuilder.append("\n dns地址2：" + dns2);
                //以太网IP地址
                String localIpAddress = getLocalIpAddress();
                netInfoStrBuilder.append("\n以太网地址：" + localIpAddress);

                String dns[] = getDnsFromCommand();
                for(int i=0; i<dns.length;i++) {
                    netInfoStrBuilder.append("\ndns"  + i + "：" + dns[i]);
                }
                mTvNetInfo.setText(netInfoStrBuilder.toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        outIp =  getOutNetIp();
                        netInfoStrBuilder.append("\n" + "公网出口Ip：" + outIp);
                        String ipAddress = getIpByDn(mEtInputDn.getText().toString());
                        netInfoStrBuilder.append("\n域名解析Ip地址：" + ipAddress);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "bright#公网出口Ip：" + outIp);
                                mTvNetInfo.setText(netInfoStrBuilder.toString());
                            }
                        });
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String inputDn = mEtInputDn.getText().toString();
                        parsedIpArray = getIPAddressFromDomainName(inputDn);
                        isPingGood = pingDn(inputDn);
                        if(!isPingGood) {
                            netDelayTime = "-1";
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                netInfoStrBuilder.append("\n\n" + "是否Ping通：" + isPingGood );
                                netInfoStrBuilder.append("\n" + "网络延迟：" + netDelayTime + "ms" );
                                if(parsedIpArray != null && parsedIpArray.length > 0) {
                                    for (int i = 0; i < parsedIpArray.length; i++) {
                                        netInfoStrBuilder.append("\n" + "解析的ip地址：" + parsedIpArray[i]);
                                    }
                                }
                                mTvNetInfo.setText(netInfoStrBuilder);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    //wifi情况下,获取到是内网Dns;Android8无法获取到dns
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
    public static String getOutNetIp() {
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
    private String[] getIPAddressFromDomainName(String host) {
        //网络正常
        if(TextUtils.isEmpty(host)) {
            return null;
        }
        String[] ipAddressArr = null;
        try {
            InetAddress[] inetAddressesArr = InetAddress.getAllByName(host);
            if(inetAddressesArr != null && inetAddressesArr.length >0) {
                ipAddressArr = new String[inetAddressesArr.length];
                for (int i = 0;i < inetAddressesArr.length;i++) {
                    ipAddressArr[i] = inetAddressesArr[i].getHostAddress();
                }
            }
        } catch (UnknownHostException e) {
            //域名无效，网络异常
            e.printStackTrace();
            return null;
        }
        return ipAddressArr;
    }

    //ping
    //https://blog.csdn.net/ccc20134/article/details/46830573
    //https://blog.csdn.net/djfgduyhgfu/article/details/70251116
    private boolean pingDn(String host) {
        if(TextUtils.isEmpty(host)) {
            return false;
        }
        String dn = host;
        try {
            Process process = Runtime.getRuntime().exec("ping -c 4 -w 100 " + dn); //ping 1次
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str = new String();
            while ((str = bufferedReader.readLine()) != null){
                if(str.contains("avg")) {
                    int i = str.indexOf("/", 20);
                    int j = str.indexOf(".", i);
                    netDelayTime = str.substring(i+1, j);
                    Log.v(TAG, "bright#延迟：" + str.substring(i+1, j) + "ms");
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
            Log.v(TAG,"bright");
        }
        return false;
    }

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                       String ipAddress = inetAddress.getHostAddress().toString();
                        if(!ipAddress.contains("::"))
                            return inetAddress.getHostAddress().toString();
                    }else
                        continue;
                }
            }
        } catch (SocketException ex) {
            Log.e("", ex.toString());
        }
        return "GetHostIP Fail,Please clear the shareReference";
    }

    //域名解析的Ip
    private String getIpByDn(String host) {
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
