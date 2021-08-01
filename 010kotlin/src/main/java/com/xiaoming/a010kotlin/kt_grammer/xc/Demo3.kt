package com.xiaoming.a010kotlin.kt_grammer.xc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 挂起 函数 标记
suspend fun main() {
    val job = GlobalScope.launch {
        repeat(1000) {
            delay(10)
            println("111111111 $it")
        }
    }

    println("A")

    // 我只等你 100毫秒，时间到，我就结束你
    Thread.sleep(100)
    // job.cancel() // 一点点的时间差，不是非常

    job.cancelAndJoin() // 一点点的时间差，都不允许

}