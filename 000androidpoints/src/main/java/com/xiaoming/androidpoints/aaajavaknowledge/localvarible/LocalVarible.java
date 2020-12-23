package com.xiaoming.androidpoints.aaajavaknowledge.localvarible;

public class LocalVarible {

    //改变局部变量的值
    public static void main() {
        String str = "abcd";
        System.out.println("bright#改变局部变量值前#str=" + str);
        changeValue(str);
        System.out.println("bright#改变局部变量值后#str=" + str);
    }

    private static void changeValue(String str) {
        str = "efgh";
        System.out.println("bright#改变局部变量值时#str=" + str);
    }
}
