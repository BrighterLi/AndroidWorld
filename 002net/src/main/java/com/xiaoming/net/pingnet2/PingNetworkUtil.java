package com.xiaoming.net.pingnet2;

import android.util.Log;


import com.xiaoming.net.pingnet2.bean.PingNetworkBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingNetworkUtil {
    private static final String TAG = "PingNetworkUtil";

    public static PingNetworkBean ping(PingNetworkBean pingNetworkBean) {
        String line = null;
        Process process = null;
        BufferedReader successReader = null;

        String command = "ping -c " + pingNetworkBean.getPingCount() + " -w " + pingNetworkBean.getPingWaitTime() + " " + pingNetworkBean.getHost();
        try {
            process = Runtime.getRuntime().exec(command);
            if (process == null) {
                Log.e(TAG, "ping fail:process is null.");
                pingNetworkBean.setPingTime(null);
                pingNetworkBean.setSuccess("0");
                return pingNetworkBean;
            }
            successReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String parsedId;
            if ((parsedId = getParsedIp(successReader.readLine())) != null) {
                pingNetworkBean.setParsedIp(parsedId);
            }
            while ((line = successReader.readLine()) != null) {
                String time;
                if ((time = getTime(line)) != null) {
                    pingNetworkBean.setPingTime(time);
                }
            }
            int status = process.waitFor();
            if (status == 0) {
                Log.i(TAG, "exec cmd success:" + command);
                pingNetworkBean.setSuccess("1");
            } else {
                Log.e(TAG, "exec cmd fail.");
                pingNetworkBean.setPingTime(null);
                pingNetworkBean.setSuccess("0");
            }
            Log.i(TAG, "exec finished.");
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (InterruptedException e) {
            Log.e(TAG, String.valueOf(e));
        } finally {
            Log.i(TAG, "ping exit.");
            if (process != null) {
                process.destroy();
            }
            if (successReader != null) {
                try {
                    successReader.close();
                } catch (IOException e) {
                    Log.e(TAG, String.valueOf(e));
                }
            }
        }
        return pingNetworkBean;
    }

    private static String getTime(String line) {
        String[] lines = line.split("\n");
        String time = null;
        for (String l : lines) {
            if (!l.contains("time="))
                continue;
            int index = l.indexOf("time=");
            time = l.substring(index + "time=".length());
            Log.i(TAG, time);
        }
        return time;
    }

    private static String getParsedIp(String line) {
        String parsedIp = null;
        if(line != null) {
            String[] lines = line.split("\n");
            if (lines[0] != null) {
                String[] lineElements = lines[0].split(" ");
                if (lineElements != null && lineElements[2] != null && lineElements.length > 2)
                    parsedIp = lineElements[2].substring(1, lineElements[2].length() - 1);  //包含beginIndex,不包含endIndex
            }
        }
        return parsedIp;
    }
}
