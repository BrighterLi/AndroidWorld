package com.a006designmode.creationmode.factorymode.factorymethodmode;

//海尔工厂
public class HaierFactory implements Factory {

    @Override
    public void make() {
        //海尔工厂生产海尔空调
        HaierAirConditioner h = new HaierAirConditioner();
        h.show();
    }
}
