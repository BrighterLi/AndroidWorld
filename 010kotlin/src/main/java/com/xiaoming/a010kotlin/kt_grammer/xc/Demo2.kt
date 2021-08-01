package com.xiaoming.a010kotlin.kt_grammer.xc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 到 阻塞协程  阻塞我们的执行
fun main() :Unit = runBlocking {// 外协程

    // 非阻塞， 类似与 守护线程
    GlobalScope.launch {
        delay(1000)
        println("1111111111111")
    }

    // 阻塞式执行的
    println("A")

    // 阻塞式执行的
    delay(300)

    // 阻塞式执行的
    println("B")

    // main 结束
}