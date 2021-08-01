package com.xiaoming.a010kotlin.kt_grammer.generic.obj

val fuClass = FuClass()
val ziClass = ZiClass()

fun test01() {
    // out 只能取，不能修改   == ? extends FuClass
    val list: MutableList<out FuClass> = ArrayList<ZiClass>()

    // in 只能修改（存）， 不能获取   === ？ super ZiClass
    val list2: MutableList<in ZiClass> = ArrayList<FuClass>()
}