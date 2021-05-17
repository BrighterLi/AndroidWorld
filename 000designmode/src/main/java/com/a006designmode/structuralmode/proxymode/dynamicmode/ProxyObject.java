package com.a006designmode.structuralmode.proxymode.dynamicmode;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyObject implements InvocationHandler {

    //代码的对象
    public Object targetObject;

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用，传入一个目标对象，和对应的对象参数
        System.out.println("代理前 你可以做的事情");
        Object object = method.invoke(targetObject, args);
        System.out.println("代理后 你可以做的事情");
        return object;
    }

}
