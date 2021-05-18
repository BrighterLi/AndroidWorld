package com.a006designmode.structuralmode.proxymode.dynamicmode.demo;

import com.a006designmode.structuralmode.proxymode.dynamicmode.demo.ProxyInterFace;

//代理的对象
public class TargetObject implements ProxyInterFace {

    public void proxyMethod() {
        System.out.println("bright8#我被代理了，哈哈！");
    }
}
