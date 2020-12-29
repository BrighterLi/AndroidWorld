package com.a006designmode.creationmode.buidermode2;

//计算机抽象类，即Product角色
public abstract class Computer {
    private String mBoard;
    private String mDisplay;
    private String mOS;

    public Computer() {

    }

    // 设置主机
    public void setBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    // 设置显示器
    public void setDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }

    // 设置操作系统
    public void setOS(String mOS) {
        this.mOS = mOS;
    }

    @Override
    public String toString() {
        return "Computer:" +
                " mBoard =" + mBoard +
                " mDisplay =" + mDisplay +
                " mOS =" + mOS;
    }
}
