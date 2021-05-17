package com.a006designmode.structuralmode.proxymode;

import com.a006designmode.structuralmode.proxymode.dynamicmode.ProxyInterFace;
import com.a006designmode.structuralmode.proxymode.dynamicmode.ProxyObject;
import com.a006designmode.structuralmode.proxymode.staticmode.Sourceable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    //Android插件化开发基础之静态代理模式:https://blog.csdn.net/u011068702/article/details/51765578
    public static void staticProxyTest() {
        Sourceable source = new Proxy();
        source.method();
    }

    //动态代理（proxy）机制:https://blog.csdn.net/u011068702/article/details/53185210
    public static void dynamicProxyTest() {
        //代理的目标对象
        ProxyInterFace  target = new TargetObject();
        //代理器
        ProxyObject proxy =  new ProxyObject();	proxy.setTargetObject(target);
        //需要传进函数的handler
        InvocationHandler handler = proxy;
        // 生存新的代理对象
        Object newProxyObject = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        //新的代理对象执行方法
        ((ProxyInterFace)newProxyObject).proxyMethod();
    }
}
