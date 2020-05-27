//
// Created by BrightLi on 2020/5/28.
//
#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_xiaoming_ndk_HelloNDK_sayHello(JNIEnv *env, jclass type) {
    return (*env)->NewStringUTF(env,"Hello NDK From C");
}

