package com.a006designmode.creationmode.factorymode.factorymethodmode;

//海尔空调实现产品接口
public class HaierAirConditioner implements Product {
    @Override
    public void show() {
        System.out.println("bright#工厂生产海尔空调");
    }
}
