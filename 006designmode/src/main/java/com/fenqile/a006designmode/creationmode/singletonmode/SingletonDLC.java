package com.fenqile.a006designmode.creationmode.singletonmode;
//Android 单例模式:https://www.jianshu.com/p/9c32aea34b6d

//DCL式(Double Check Lock)

//1、构造函数用private修饰，外部无法访问
//2、使用的时候即调用getInstance的时候才初始化
//3、static关键字修饰，静态变量，存储在内存中，只有一份数据
//4、synchronized线程安全，多线程情况下单例的唯一性
//5、两次判断空，避免多次同步(synchronized)
//缺点
//private static SingletionDLC mInstance;
//private SingletionDLC() {}
//public static SingletionDLC getmInstance() {}
//由于jvm特性，允许乱序执行，上面三句代码顺序不定，那么就可能出现DCL失效的问题。
//步骤一、倘若A线程执行getmInstance(),还没执行构造方法SingletionDLC()
//步骤二、此时B线程调用getmInstance()。因为A已经执行getmInstance()，所以mInstance不为空就直接获取。
//步骤三、由于B直接获取，而真实情况是A线程构造方法还未执行，所以mInstance就为空了。
//虽然此情况发生概率较小，但也是一种情况。为了解决这种情况，java1.6开始加入volatile关键字
//private volatile static SingletionDLC mInstance;
//这样就避免了DCL方式失效的情况。虽然会volatile消耗一些性能，所以DCL最佳写法

public class SingletonDLC {

    private volatile static SingletonDLC mInstance; //volatile线程安全

    private SingletonDLC() {}

    public static SingletonDLC getInstance() {
        if(mInstance == null) {
            synchronized (SingletonDLC.class) {
                if(mInstance == null) {
                    mInstance = new SingletonDLC();
                }
            }
        }
        return mInstance;
    }
}
