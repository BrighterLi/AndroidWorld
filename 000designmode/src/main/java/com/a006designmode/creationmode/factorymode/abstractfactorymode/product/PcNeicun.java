package com.a006designmode.creationmode.factorymode.abstractfactorymode.product;

import com.a006designmode.creationmode.factorymode.abstractfactorymode.product.Neicun;

//实现内存接口
public class PcNeicun implements Neicun {

    @Override
    public void NeicunMake() {
        System.out.println("bright#Pc工厂生产内存");
    }
}
