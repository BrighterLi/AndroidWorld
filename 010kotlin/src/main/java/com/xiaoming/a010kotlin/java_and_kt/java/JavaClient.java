package com.xiaoming.a010kotlin.java_and_kt.java;

import com.xiaoming.a010kotlin.java_and_kt.kt.MyUtils;
import com.xiaoming.a010kotlin.java_and_kt.kt.MyUtilsKt;

public class JavaClient {

    public static void main(String[] args) {
        ///Java调用Kotlin
        MyUtilsKt.show("bright");
        new MyUtils().show("new bright2");
    }
}
