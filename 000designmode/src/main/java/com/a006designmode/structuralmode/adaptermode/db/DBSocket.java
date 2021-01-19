package com.a006designmode.structuralmode.adaptermode.db;

//德国插座实现德标接口
public class DBSocket implements DBSocketInterface {

    @Override
    public void powerWithTwoRound() {
        System.out.println("bright#使用德国两项圆头的插孔供电");
    }
}
