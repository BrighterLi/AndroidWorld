package com.xiaoming.androidpoints.aaajavaknowledge.valuetransition;

public class ValueTransition {
    static int x;
    //基本类型值传递
    public static void main() {
        //int x;
        x = 1;
        ValueTransition valueTransition = new ValueTransition();
        System.out.println("bright#x1=" + x);
        //传递过去的是基本类型的值
        valueTransition.setValue(x);
        System.out.println("bright#x3=" + x);
    }

    public void setValue(int x) {
        x = x + 1;
        System.out.println("bright#x2=" + x);
    }
}
