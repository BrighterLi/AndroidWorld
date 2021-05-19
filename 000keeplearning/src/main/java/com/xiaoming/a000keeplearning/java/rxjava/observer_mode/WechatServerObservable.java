package com.xiaoming.a000keeplearning.java.rxjava.observer_mode;

import java.util.ArrayList;
import java.util.List;

// 被观察者 实现
public class WechatServerObservable implements Observable {

    // 容器 存储 多个  观察者
    private List<Observer> observers = new ArrayList<>();
    private String message;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        // 遍历容器
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public void pushMessage(String message) {
        this.message = message;
        // System.out.println("微信服务号更新了消息：" + message);

        // 通知所有 关注了 此服务号的 用户
        notifyObservers();
    }
}
