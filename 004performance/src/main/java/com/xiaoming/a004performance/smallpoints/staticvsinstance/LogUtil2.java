package com.xiaoming.a004performance.smallpoints.staticvsinstance;

import android.util.Log;

//静态方法实现工具类
public class LogUtil2 {
    /**
     * 打印日志测方法
     * @param content
     */
    public static void print(String content){
        Log.i("tag",content);
    }
}
