package com.a006designmode.creationmode.factorymode.easyfactorymode;

import android.util.Log;

//创建具体产品角色（角色可以创建多个，这里创建了2个），继承抽象产品类
public class ProductB extends Product {

    @Override
    public void make() {
        Log.e("bright","Product:产品B");
    }
}
