package com.xiaoming.ndk;

public class HelloNDK {
    public static native String sayHello();


    static {
        System.loadLibrary("HelloNDK"); //加载库文件
    }
}
