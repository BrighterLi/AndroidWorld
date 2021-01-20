package com.a006designmode.creationmode.factorymode.abstractfactorymode;

import com.a006designmode.creationmode.factorymode.abstractfactorymode.factory.MacFactory;
import com.a006designmode.creationmode.factorymode.abstractfactorymode.factory.PcFactory;

//简单工厂模式， 工厂方法模式，抽象工厂模式：https://blog.csdn.net/weixin_44705662/article/details/89846955
public class AbstractFactoryMode {

    public static void test() {
        //输入选项，根据选项来决定生产哪个工厂的产品
        String input = "P";
        //String input = "M";
        if(input.equals("P")) {
            PcFactory p = new PcFactory();
            p.make();
        } else if(input.equals("M")) {
            MacFactory m = new MacFactory();
            m.make();
        }
    }
}
