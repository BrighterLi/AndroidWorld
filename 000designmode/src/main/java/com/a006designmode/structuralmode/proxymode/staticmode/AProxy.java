package com.a006designmode.structuralmode.proxymode.staticmode;


public class AProxy implements Sourceable {
    private Source source;

    public AProxy() {
        super();
        this.source = new Source();
    }

    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }

    private void atfer() {
        System.out.println("after proxy!");
    }

    private void before() {
        System.out.println("before proxy!");
    }

}
