package com.xiaoming.net.pingnet2.bean;

public class PingNetworkBean {
    private String host; //域名
    private int pingCount;
    private int pingWaitTime; //ping超时时间
    private String pingTime; //ping花费时间
    private String  parsedIp; //解析后的ip
    private String success; //1是成功，0是失败

    public PingNetworkBean(String host, int pingCount, int pingWaitTime) {
        this.host = host;
        this.pingCount = pingCount;
        this.pingWaitTime = pingWaitTime;
    }


    public String getPingTime() {
        if(pingTime == null) {
            return "-1";
        }
        return pingTime;
    }

    public void setPingTime(String pingTime) {
        this.pingTime = pingTime;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPingCount() {
        return pingCount;
    }

    public void setPingCount(int pingCount) {
        this.pingCount = pingCount;
    }

    public int getTime() {
        return pingWaitTime;
    }

    public void setPingWaitTime(int pingWaitTime) {
        this.pingWaitTime = pingWaitTime;
    }

    public int getPingWaitTime() {
        return pingWaitTime;
    }

    public String getParsedIp() {
        if(parsedIp == null) {
            return "-1";
        }
        return parsedIp;
    }

    public void setParsedIp(String parsedIp) {
        this.parsedIp = parsedIp;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
