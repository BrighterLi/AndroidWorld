package com.a006designmode.creationmode.factorymode.factorymethodmode;

//简单工厂模式， 工厂方法模式，抽象工厂模式：https://blog.csdn.net/weixin_44705662/article/details/89846955
public class FactoryMethodModeTest {

    public static void test() {
        //根据选项来决定生产哪个工厂的产品
        String input = "H";
        //String input = "M";
        if(input.equals("H")) {
            HaierFactory h = new HaierFactory();
            h.make();
        } else if(input.equals("M")) {
            MideaFactory m = new MideaFactory();
            m.make();
        }
    }
}
