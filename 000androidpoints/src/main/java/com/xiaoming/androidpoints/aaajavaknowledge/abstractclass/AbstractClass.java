package com.xiaoming.androidpoints.aaajavaknowledge.abstractclass;

import android.util.Log;

public abstract class AbstractClass {

    abstract void initial();

    public AbstractClass() {
        Log.i("AbstractClass", "bright9#AbstractClass1");
        initial();
        Log.i("AbstractClass", "bright9#AbstractClass2");
    }
}
