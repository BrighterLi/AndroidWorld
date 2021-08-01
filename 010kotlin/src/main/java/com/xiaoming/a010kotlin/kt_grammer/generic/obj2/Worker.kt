package com.xiaoming.a010kotlin.kt_grammer.generic.obj2

// 这个类，就是只能获取，不能修改了
// 声明的时候加入  一劳永逸了<in T>
class Worker <out T> {

    // 能获取
    fun getData() : T? = null

    // 不能修改
    /*fun setData(data: T) {

    }

    fun addData(data: T) {

    }*/
}