package com.a006designmode.behaviormode.observermode2;


//具体观察者（ConcrereObserver）
public class WeixinUser implements Observer {
    // 微信用户名
    private String name;
    public WeixinUser(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println("bright8" + name + "-" + message);
    }
}
