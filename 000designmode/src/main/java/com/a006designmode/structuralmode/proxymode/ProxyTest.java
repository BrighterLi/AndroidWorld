package com.a006designmode.structuralmode.proxymode;

import com.a006designmode.structuralmode.proxymode.dynamicmode.demo.ProxyInterFace;
import com.a006designmode.structuralmode.proxymode.dynamicmode.demo.ProxyObject;
import com.a006designmode.structuralmode.proxymode.dynamicmode.demo.TargetObject;
import com.a006designmode.structuralmode.proxymode.dynamicmode.demo2.LogHandler;
import com.a006designmode.structuralmode.proxymode.dynamicmode.demo2.UserService;
import com.a006designmode.structuralmode.proxymode.dynamicmode.demo2.UserServiceImpl;
import com.a006designmode.structuralmode.proxymode.staticmode.AProxy;
import com.a006designmode.structuralmode.proxymode.staticmode.Sourceable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    //Android插件化开发基础之静态代理模式:https://blog.csdn.net/u011068702/article/details/51765578
    public static void staticProxyTest() {
        Sourceable source = new AProxy();
        source.method();
    }

    //动态代理:https://blog.csdn.net/u011068702/article/details/53185210
    public static void dynamicProxyTest() {
        //代理的目标对象
        ProxyInterFace  target = new TargetObject();
        //代理器
        ProxyObject proxy =  new ProxyObject();
        proxy.setTargetObject(target);
        //需要传进函数的handler
        InvocationHandler handler = proxy;
        // 生成新的代理对象
        Object newProxyObject = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        //新的代理对象执行方法
        ((ProxyInterFace)newProxyObject).proxyMethod();
    }

    //Java动态代理:https://www.cnblogs.com/whirly/p/10154887.html
    /**
    * 编写一个调用逻辑处理器 LogHandler 类，提供日志增强功能，并实现 InvocationHandler 接口；在 LogHandler 中维护一个目标对象，   *  这个对象是被代理的对象（真实主题角色）；在 invoke 方法中编写方法调用的逻辑处理
    **/
    public static void dynamicProxyTest2() {
        // 设置变量可以保存动态代理类，默认名称以 $Proxy0 格式命名
        // System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 1. 创建被代理的对象，UserService接口的实现类
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        // 2. 获取对应的 ClassLoader
        ClassLoader classLoader = userServiceImpl.getClass().getClassLoader();
        // 3. 获取所有接口的Class，这里的UserServiceImpl只实现了一个接口UserService，
        Class[] interfaces = userServiceImpl.getClass().getInterfaces();
        // 4. 创建一个将传给代理类的调用请求处理器，处理所有的代理对象上的方法调用
        //     这里创建的是一个自定义的日志处理器，须传入实际的执行对象 userServiceImpl
        InvocationHandler logHandler = new LogHandler(userServiceImpl);
        /*
		   5.根据上面提供的信息，创建代理对象 在这个过程中，
               a.JDK会通过根据传入的参数信息动态地在内存中创建和.class 文件等同的字节码
               b.然后根据相应的字节码转换成对应的class，
               c.然后调用newInstance()创建代理实例
		 */
        UserService proxy = (UserService) Proxy.newProxyInstance(classLoader, interfaces, logHandler);
        // 调用代理的方法
        proxy.select();
        proxy.update();

        // 保存JDK动态代理生成的代理类，类名保存为 UserServiceProxy
        // ProxyUtils.generateClassFile(userServiceImpl.getClass(), "UserServiceProxy");
    }

}
