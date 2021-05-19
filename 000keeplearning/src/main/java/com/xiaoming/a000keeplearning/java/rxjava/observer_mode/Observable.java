package com.xiaoming.a000keeplearning.java.rxjava.observer_mode;

// TODO 抽象层 被观察者
public interface Observable {

    // 关注
    void addObserver(Observer observer);

    // 取消关注
    void removeObserver(Observer observer);

    // 被观察者发出了改变
    void notifyObservers();

    // 微信公众号的服务 编辑部门 发布一条消息
    void pushMessage(String message);
}
