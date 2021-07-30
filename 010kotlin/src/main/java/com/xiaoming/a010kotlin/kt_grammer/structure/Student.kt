package com.xiaoming.a010kotlin.kt_grammer.structure

//自己写一个结构
class Student(val id: Int, val name: String, var sex: Char) {

    //component不能写错
    operator fun component1(): Int = id
    operator fun component2(): String = name
    operator fun component3(): Char = sex
    operator fun component4(): String = "KT Study OK"
}

fun main() {
    val stu = Student(4545, "bright", 'M')
    val(n1, n2, n3, n4) = stu //报参数解出来
    println(n1)
    println(n2)
    println(n3)
    println(n4)
}