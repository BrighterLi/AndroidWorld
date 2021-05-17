package com.xiaoming.a000keeplearning.java.rxjava.observer_mode;

// 观察者 实现
public class UserPerson implements Observer {

    private String name;
    private String message;

    public UserPerson(String name) {
        this.name = name;
    }

    @Override
    public void update(Object value) {
        this.message = (String) value;

        // 读取消息
        readMessage();
    }

    private void readMessage() {
        System.out.println(name + " 收到了推送消息：" + message);
    }
}
