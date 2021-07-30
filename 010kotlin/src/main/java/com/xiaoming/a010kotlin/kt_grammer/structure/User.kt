package com.xiaoming.a010kotlin.kt_grammer.structure

data class User(val id: Int, val name: String, val sex: Char)

//kt自己就有的结构
fun main() {
    val u = User(1, "bright", 'M')
    val (v1, v2, v3) = u.copy()
    println("v1:$v1, v2:$v2, v3: $v3")
}