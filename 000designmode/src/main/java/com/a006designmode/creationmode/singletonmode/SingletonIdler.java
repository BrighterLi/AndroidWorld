package com.a006designmode.creationmode.singletonmode;

//懒汉式

//1、构造函数用private修饰，外部无法访问
//2、使用的时候即调用getInstance的时候才初始化
//3、static关键字修饰，静态变量，存储在内存中，只有一份数据。
//4、synchronized线程安全，多线程情况下单例的唯一性
//5、缺点：没次调用getInstance都会同步一次，浪费资源

public class SingletonIdler {
    private static SingletonIdler mInstance;

    private SingletonIdler() {

    }

    public static synchronized SingletonIdler getInstance() {
        if(mInstance == null) {
            mInstance = new SingletonIdler();
        }
        return mInstance;
    }
}
