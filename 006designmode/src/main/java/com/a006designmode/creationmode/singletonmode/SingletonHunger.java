package com.a006designmode.creationmode.singletonmode;

//饿汉模式

//1、构造函数用private修饰，外部无法访问
//2、声明静态对象时就初始化
//3、static关键字修饰，静态变量，存储在内存中，只有一份数据。
//4、final关键字，只初始化一次，所以mInstance实例只有一个。

public class SingletonHunger {

    private static final SingletonHunger mInstance = new SingletonHunger();

    private SingletonHunger() {

    }

    public static SingletonHunger getInstance() {
        return mInstance;
    }
}
