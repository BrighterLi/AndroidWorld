package com.a006designmode.creationmode.buidermode2;

//MabBookBuilder
public class MabBookBuilder extends  Builder{
    private Computer mComputer = new MacBook();

    @Override
    public void buildBoard(String board) {
        mComputer.setBoard(board);
    }

    @Override
    public void buildDisplay(String display) {
        mComputer.setDisplay(display);
    }

    @Override
    public void buildOS(String os) {
        mComputer.setOS(os);
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}
