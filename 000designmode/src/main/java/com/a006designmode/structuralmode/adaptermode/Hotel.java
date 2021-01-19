package com.a006designmode.structuralmode.adaptermode;

import com.a006designmode.structuralmode.adaptermode.db.DBSocketInterface;

//德国旅馆是一个客户端，它里面有德标的接口，可以使用这个德标接口给手机充电
public class Hotel {

    //旅馆中有一个德标的插口
    private DBSocketInterface dbSocket;

    public Hotel(){}

    public Hotel(DBSocketInterface dbSocket) {
        this.dbSocket = dbSocket;
    }

    public void setSocket (DBSocketInterface dbSocket){
        this.dbSocket = dbSocket;
    }

    //旅馆中有一个充电的功能
    public void charge(){

        //使用德标插口充电
        dbSocket.powerWithTwoRound();
    }
}
