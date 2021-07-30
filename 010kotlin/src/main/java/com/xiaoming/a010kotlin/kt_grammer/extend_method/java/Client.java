package com.xiaoming.a010kotlin.kt_grammer.extend_method.java;

import java.io.File;

import kotlin.io.FilesKt;
import kotlin.text.Charsets;

public class Client {

    void test() {
        File file = new File("xxx");
        //Java调用扩展函数
        FilesKt.readText(file, Charsets.UTF_8);
    }
}
