package com.a006designmode.creationmode.factorymode.factorymethodmode;

//美的空调实现产品接口
public class MideaAirConditioner implements Product {
    @Override
    public void show() {
        System.out.println("工厂生产美的空调");
    }
}
