package com.xiaoming.framemvp.modle;

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
