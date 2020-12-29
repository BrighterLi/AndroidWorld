package com.a006designmode.creationmode.buidermode2;

//Director，负责构造Computer
public class Director {
    Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**

     构建对象
     @param board 主板
     @param display 显示器
     @param os 操作系统
     */
    public void construct(String board, String display, String os) {
        builder.buildBoard(board);
        builder.buildDisplay(display);
        builder.buildOS(os);
    }
}
