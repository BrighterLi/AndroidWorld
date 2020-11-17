package com.a006designmode.behaviormode.observermode;

//被观察者接口
public interface SubjectListener {
    void add(ObserverListener observerListener);

    void notifyObserver(String content);

    void remove(ObserverListener observerListener);
}
