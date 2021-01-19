package com.a006designmode.structuralmode.adaptermode.gb;

//中国插座
public class GBSocket implements GBSocketInterface {

    @Override
    public void powerWithThreeFlat() {
        System.out.println("bright#使用中国的三项扁头插孔供电");
    }
}
