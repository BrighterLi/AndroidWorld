package com.xiaoming.mvx.mvp.modle;

//这里模拟数据模拟的很简单粗暴，直接一个单例模式搞定只是为了写demo,你可以写自己的数据库，或者网络操作，或者其他
public class Data {
    private static Data INSTANCE;

    public static Data getInstance() {
        if(INSTANCE == null) {
            synchronized (Data.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Data();
                }
            }
        }
        return INSTANCE;
    }

    public String getName() {
        return "jack";
    }
}
