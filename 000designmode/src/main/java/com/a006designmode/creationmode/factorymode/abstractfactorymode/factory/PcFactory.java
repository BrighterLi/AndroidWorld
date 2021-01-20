package com.a006designmode.creationmode.factorymode.abstractfactorymode.factory;

import com.a006designmode.creationmode.factorymode.abstractfactorymode.product.PcCPU;
import com.a006designmode.creationmode.factorymode.abstractfactorymode.product.PcNeicun;
import com.a006designmode.creationmode.factorymode.factorymethodmode.Factory;

//实现工厂接口
public class PcFactory implements Factory {

    @Override
    public void make() {
        System.out.println("bright#Pc工厂生产配件中...");
        PcNeicun p1 = new PcNeicun();
        p1.NeicunMake();
        PcCPU p2 = new PcCPU();
        p2.CPUMake();
    }
}
