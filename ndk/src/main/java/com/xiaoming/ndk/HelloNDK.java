package com.xiaoming.ndk;

public class HelloNDK {
    public static native String sayHello();

    public static native int add(int a, int b);

    static {
        System.loadLibrary("HelloNDK"); //加载库文件
    }
}
