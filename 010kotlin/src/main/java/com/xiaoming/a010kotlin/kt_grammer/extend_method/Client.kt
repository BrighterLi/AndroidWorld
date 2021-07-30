package com.xiaoming.a010kotlin.kt_grammer.extend_method

import java.io.File

fun main() {
    val stu = Student()
    stu.add(100, 200)

    //Kotlin本来就有扩展函数
    val file = File("///")
    file.readText()
    file.show()
}

//扩展函数,给Student添加函数，Student原先没有这个函数
fun Student.add(n1: Int, n2: Int) {
    println("结果: ${n1+n2}")
}

fun Student.show() {
    println("Student show")
}

//给java File增加扩展函数
fun File.show() {
    println("给java File增加扩展函数")
}