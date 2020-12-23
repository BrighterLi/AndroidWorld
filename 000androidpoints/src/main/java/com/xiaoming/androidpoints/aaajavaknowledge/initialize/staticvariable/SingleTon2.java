package com.xiaoming.androidpoints.aaajavaknowledge.initialize.staticvariable;

public class SingleTon2 {
    public static int count1;
    public static int count2 = 1;
    private static SingleTon2 singleTon = new SingleTon2();

    private SingleTon2() {
        count1++;
        count2++;
    }

    public static SingleTon2 getInstance() {
        return singleTon;
    }
}
