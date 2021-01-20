package com.a006designmode.creationmode.factorymode.abstractfactorymode.factory;

import com.a006designmode.creationmode.factorymode.abstractfactorymode.product.MacCPU;
import com.a006designmode.creationmode.factorymode.abstractfactorymode.product.MacNeicun;
import com.a006designmode.creationmode.factorymode.factorymethodmode.Factory;

//实现工厂接口
public class MacFactory implements Factory {
    @Override
    public void make() {
        System.out.println("bright#Mac工厂生产配件中...");
        MacNeicun m1 = new MacNeicun();
        m1.NeicunMake();
        MacCPU m2 = new MacCPU();
        m2.CPUMake();
    }
}
