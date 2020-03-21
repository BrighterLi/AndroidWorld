package com.xiaoming.a002netcache.cache.mapcache;

import android.os.Looper;

import java.util.logging.Logger;

// 监听失效数据并移除
public class CacheListener {
    Logger logger = Logger.getLogger("cacheLog");
    private CacheManagerImpl cacheManagerImpl;

    public CacheListener(CacheManagerImpl cacheManagerImpl) {
        this.cacheManagerImpl = cacheManagerImpl;
    }

    public void startListen() {
        new Thread() {
            @Override
            public void run() {
                //一直执行，达到监听效果，什么时候停止？
                while (true) {
                    for(String key : cacheManagerImpl.getAllKeys()) {
                        //超时则移除
                        if(cacheManagerImpl.isTimeOut(key)) {
                            cacheManagerImpl.clearByKey(key);
                            logger.info(key+"缓存被清除");
                        }
                    }
                }
            }
        }.start();
    }
}
