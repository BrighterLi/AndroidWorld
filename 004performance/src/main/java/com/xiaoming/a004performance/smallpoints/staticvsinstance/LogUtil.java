package com.xiaoming.a004performance.smallpoints.staticvsinstance;

import android.util.Log;

//单例实现工具类
public class LogUtil {
    private static volatile LogUtil instance;
    private LogUtil(){};

    /**
     * 单例模式 获取实例方法
     * @return
     */
    public static LogUtil getInstance(){
        if (instance == null){
            synchronized (LogUtil.class){
                if (instance == null){
                    instance = new LogUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 打印日志测方法
     * @param content
     */
    public void print(String content){
        Log.i("tag",content);
    }
}
