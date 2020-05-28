//
// Created by brightli on 2020/5/28.
//
#include <jni.h>
#include <string>

extern "C" JNIEXPORT jint Java_com_xiaoming_ndk_HelloNDK_add(JNIEnv *env, jobject obj, jint a, jint b) {
    int ia = a;
    int ib = b;
    printf("bright#a+b：", a+b);
    return a+b;
}

