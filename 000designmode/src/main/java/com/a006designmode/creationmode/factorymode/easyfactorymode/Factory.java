package com.a006designmode.creationmode.factorymode.easyfactorymode;

//工厂角色，进行下面产品方法的实现
public class Factory {

    public static Product create(String productType) {
        Product product = null;
        switch (productType) {
            case "1":
                product = new ProductA();
                break;
            case "2":
                product = new ProductB();
                break;
        }
        return product;
    }
}
