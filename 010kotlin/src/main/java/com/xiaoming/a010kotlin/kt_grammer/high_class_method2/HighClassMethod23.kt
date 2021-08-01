package com.xiaoming.a010kotlin.kt_grammer.high_class_method2

fun main() {
    // Unit
    // String
    // Boolean
    // Double
    sum(10, 20,30) { i1, i2, i3 ->
        println("i1:$i1, i2:$i2, i3:$i3")
        "OK"
        true
        99999.343
    }
}

// TODO 三数相乘
fun <R> sum(n1: Int, n2: Int, n3: Int, mm: (Int, Int, Int) -> R): R {
    return mm(n1, n2, n3)
}