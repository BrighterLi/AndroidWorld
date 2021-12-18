package com.xiaoming.androidpoints.aaajavaknowledge.abstractclass.constructorfun2;


abstract class Person {   //定义一个抽象类，必须被继承

    public  Person(){
        System.out.println("bright9#调用的是父类的无参构造函数");
    }
    public   Person(int i) {
        System.out.println("bright9#调用的是父类的有参构造函数");
    }
}

