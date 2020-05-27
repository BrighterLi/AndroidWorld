package com.xiaoming.net.netdetect;

public class PingNetBean {
    private String domainName;
    private String ip;
    private boolean isPingGood;
    private int netDelayTime;

    public PingNetBean(String domainName, String ip, boolean isPingGood) {
        this.domainName = domainName;
        this.ip = ip;
        this.isPingGood = isPingGood;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isPingGood() {
        return isPingGood;
    }

    public void setPingGood(boolean pingGood) {
        isPingGood = pingGood;
    }
}
