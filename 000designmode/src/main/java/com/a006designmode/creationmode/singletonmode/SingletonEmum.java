package com.a006designmode.creationmode.singletonmode;

//枚举单例

//枚举在java中和普通的类一样，可以有字段和自己的方法。
// 枚举实例的创建时线程安全并且任何情况下它都是一个单例。包括反序列化的时候。

public enum  SingletonEmum {
    INSTANCE;
    public void doSomething() {}
}
