package com.xiaoming.androidknowledgepoints.ajavaknowledge.valuetransition;

public class ValueTransition2 {

    //引用类型值传递
    public static void  main() {
        ValueTransition2 valueTransition2 = new ValueTransition2();
        valueTransition2.show();
    }

    public void show() {
        Value2 v = new Value2();
        v.setS(100.51);
        System.out.println("bright#调用changeValue方法之前，x=" + v.getS());
        //传递过去的是对象
        changeValue(v);
        System.out.println("bright#调用changeValue方法之后，x=" + v.getS());
    }

    public void changeValue(Value2 value) {
        double v = value.getS();
        value.setS(v * 2);
        System.out.println("bright#调用changeValue方法时，x=" + value.getS());
    }
}
