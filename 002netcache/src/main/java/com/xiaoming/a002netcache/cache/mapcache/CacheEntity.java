package com.xiaoming.a002netcache.cache.mapcache;

//缓存对象类
public class CacheEntity {
    //保存的数据
    private Object datas;
    //设置数据失效时间，为0表示永远不失效
    private long timeOut;
    //最后刷新时间
    private long lastRefreshTime;

    public CacheEntity(Object datas, long timeOut, long lastRefreshTime) {
        this.datas = datas;
        this.timeOut = timeOut;
        this.lastRefreshTime = lastRefreshTime;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public void setLastRefreshTime(long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    public Object getDatas() {
        return datas;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public long getLastRefreshTime() {
        return lastRefreshTime;
    }
}
