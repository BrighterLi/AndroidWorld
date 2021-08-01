package com.xiaoming.a010kotlin.kt_grammer.high_class_method2

import kotlin.concurrent.thread

// TODO 实战    Kotlin有很多的 kt糖
fun main() {
    ktrun() {
        doCounts(100) {
            println("执行了一次  下标是:$it")
        }
    }

    thread {  }
}

// 自定义轮询器  传入 9  我就给你 轮询9次
fun doCounts(counts: Int, mm:(Int) -> Unit) {

    // 0 1 2 3 4 5 6 7 8
    for (index in 0 until counts) {
        mm(index)
    }

}

// 自定义线程封装
fun ktrun(
        start: Boolean = true,
        name: String ? = null,
        // ... 省略
        myRunAction: () -> Unit) : Thread {

    val thread = object : Thread() {
        override fun run() {
            super.run()

            myRunAction()
        }
    }

    name ?: "DerryThread"

    if (start)
        thread.start()

    return thread
}