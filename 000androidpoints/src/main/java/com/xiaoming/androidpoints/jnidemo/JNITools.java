package com.xiaoming.androidpoints.jnidemo;

public class JNITools {
    static {
        System.loadLibrary("androidknowledgepoints");//与Android.mk文件中设置的一致，不需要手动添加前缀
    }

    //加法
    public static native int add(int a, int b);

    //减法
    public static native int sub(int a, int b);

    //乘法
    public static native int mul(int a, int b);

    //除法
    public static native int div(int a, int b);
}
