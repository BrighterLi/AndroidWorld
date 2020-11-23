package com.xiaoming.net.cache.mapcache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

//缓存接口实现类
public class CacheManagerImpl implements ICacheManager {
    private static Map<String, CacheEntity> caches = new ConcurrentHashMap<String, CacheEntity>();

    /**
     * 存入缓存
     * @param key
     * @param cache
     */
    @Override
    public void putCache(String key, CacheEntity cache) {
        caches.put(key, cache);
    }

    /**
     * 存入缓存
     * @param key
     * @param datas
     */
    @Override
    public void putCache(String key, Object datas, long timeOut) {
        timeOut = timeOut > 0 ? timeOut : 0L;
        putCache(key, new CacheEntity(datas, timeOut, System.currentTimeMillis()));
    }

    /**
     * 获取对应缓存
     * @param key
     * @return
     */
    @Override
    public CacheEntity getCacheByKey(String key) {
        if(this.isContains(key)) {
            return caches.get(key);
        }
        return null;
    }

    /**
     * 获取对应缓存
     * @param key
     * @return
     */
    @Override
    public Object getCacheDataByKey(String key) {
        if(this.isContains(key)) {
            return caches.get(key).getDatas();
        }
        return null;
    }

    /**
     * 获取所有缓存
     * @param
     * @return
     */
    @Override
    public Map<String, CacheEntity> getCacheAll() {
        return caches;
    }

    /**
     * 判断是否在缓存中
     * @param key
     * @return
     */
    @Override
    public boolean isContains(String key) {
        return caches.containsKey(key);
    }

    /**
     * 清除所有缓存
     */
    @Override
    public void clearAll() {
        caches.clear();
    }

    /**
     * 清除对应缓存
     * @param key
     */
    @Override
    public void clearByKey(String key) {
        if (this.isContains(key)) {
            caches.remove(key);
        }
    }

    /**
     * 缓存是否超时失效
     * @param key
     * @return
     */
    @Override
    public boolean isTimeOut(String key) {
        if(!caches.containsKey(key)) {
            return true;
        }
        CacheEntity cacheEntity = caches.get(key);
        long timeOut = cacheEntity.getTimeOut();
        long lastRefreshTime = cacheEntity.getLastRefreshTime();
        if (timeOut == 0 || System.currentTimeMillis() - lastRefreshTime >= timeOut) {
            return true;
        }
        return false;
    }

    /**
     * 获取所有key
     * @return
     */
    @Override
    public Set<String> getAllKeys() {
        return caches.keySet();
    }
}
