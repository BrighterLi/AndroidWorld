package com.xiaoming.a000keeplearning.java.rxjava.observer_mode;

// TODO 抽象层 观察者
public interface Observer {

    // 被观察者改变了，我这里可以知道
    void update(Object value);

}
