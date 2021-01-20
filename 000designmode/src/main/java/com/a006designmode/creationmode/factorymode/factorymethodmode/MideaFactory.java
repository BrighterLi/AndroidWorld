package com.a006designmode.creationmode.factorymode.factorymethodmode;

//美的工厂
public class MideaFactory implements Factory{

    @Override
    public void make() {
        //美的工厂生产美的空调
        MideaAirConditioner m = new MideaAirConditioner();
        m.show();
    }
}
