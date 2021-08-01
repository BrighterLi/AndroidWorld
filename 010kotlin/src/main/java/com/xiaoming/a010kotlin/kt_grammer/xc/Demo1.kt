package com.xiaoming.a010kotlin.kt_grammer.xc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    // 非阻塞， 类似与 守护线程
    GlobalScope.launch {
        delay(1000)
        println("1111111111111")
    }

    println("A")

    // main 线程 睡眠 2秒
    // Thread.sleep(2000)

    Thread.sleep(200)

    println("B")

    // main 结束
}