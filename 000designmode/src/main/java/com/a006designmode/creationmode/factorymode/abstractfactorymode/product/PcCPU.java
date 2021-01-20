package com.a006designmode.creationmode.factorymode.abstractfactorymode.product;

import com.a006designmode.creationmode.factorymode.abstractfactorymode.product.CPU;

//实现CPU接口
public class PcCPU implements CPU {

    @Override
    public void CPUMake() {
        System.out.println("bright#Pc工厂生CPU");
    }
}
