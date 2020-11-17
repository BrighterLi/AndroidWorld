package com.a006designmode.creationmode.singletonmode;

//静态内部类方式

//1、构造函数用private修饰，外部无法访问
//2、使用的时候即调用getInstance的时候才初始化
//3、调用getInstance才回去加载SingletionInternalClassHolder类，确保了线程安全，保证了单例的唯一性

public class SingletonStatic {
    private SingletonStatic() {}

    public static SingletonStatic getInstance() {
        return SingletonStaticHolder.instance;
    }

    private static class SingletonStaticHolder {
        private static final SingletonStatic instance = new SingletonStatic();
    }
}
