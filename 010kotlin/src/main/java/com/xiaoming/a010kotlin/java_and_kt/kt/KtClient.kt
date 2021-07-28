package com.xiaoming.a010kotlin.java_and_kt.kt

import com.xiaoming.a010kotlin.java_and_kt.java.JavaStudent
import com.xiaoming.a010kotlin.java_and_kt.java.callback.JavaCallback
import com.xiaoming.a010kotlin.java_and_kt.java.callback.JavaCallbackManager
import com.xiaoming.a010kotlin.java_and_kt.kt.callback.KTCallback
import com.xiaoming.a010kotlin.java_and_kt.kt.callback.KtCallbackManager
import kotlin.reflect.KClass

fun main() {

    ///Kotlin调用Java
    //解决冲突问题，in在kotlin是关键字，可以使用'in'
    println(JavaStudent.`in`)

    //Kotlin调用Java函数
    // String!，由于是！ 你最好这样写var str: String ? =
    //JavaStudent().string.length  //错误的示范，JavaStudent().string为空会出现崩溃
    var str: String ? = JavaStudent().string
    print(str?.length)


    ///形参里是Java类或者kt类
    showClass(JavaStudent:: class.java)
    showClass2(KtStudent::class)


    ///kt使用Java callback
    //第一张写法
    JavaCallbackManager().setCallback(JavaCallback {
        println(it) })
    //第二种写法
    JavaCallbackManager().setCallback(object : JavaCallback{
        override fun show(info: String?) {
            println(info)
        }
    })
    //lamada写法
    JavaCallbackManager().setCallback { info -> println(info) }
    //第三种写法
    val callback = JavaCallback {
        println(it)
    }
    JavaCallbackManager().setCallback(callback)
    //第四种写法
    val callback2 = object : JavaCallback {
        override fun show(info: String?) {
            println(info)
        }
    }
    JavaCallbackManager().setCallback(callback2)


    ///Kt 使用 Kt Callback
    //写法1
    KtCallbackManager().setCallback(object : KTCallback {
        override fun show(name: String) {

        }
    })
    //写法2
    val c = object : KTCallback{
        override fun show(name: String) {
        }
    }
    KtCallbackManager().setCallback(c)
}

//形参里面java
fun showClass(clazz: Class<JavaStudent>) {}

//形参里是kt
fun showClass2(clazz: KClass<KtStudent>) {}