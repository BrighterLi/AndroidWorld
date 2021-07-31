package com.xiaoming.a010kotlin.kt_grammer.lambda

fun main() {

    // () -> Unit    空参函数  并且 没有返回值  函数名=method01

    // TODO 定义没有问题，调用不行
    /*var method01 : () -> Unit
    method01() // 不能调用  没有具体的实现

    var method02 : (Int, Int) -> Int
    method02(9, 9)

    var method03: (String, Double) -> Any?
    method03("Derry", 543354.4)

    var method04 : (Int, Double, Long, String ? ) -> Boolean
    method04(1, 545, 3543, null)

    var method05 : (Int, Int) -> Int
    method05(9, 9)*/


    // : (形参类型)
    // = {具体详情}

    // TODO 定义没有问题，调用OK ，因为有实现了
    var m06 : (Int, Int) -> Int = {number1, number2 -> number1 + number2}
    println("m06:${m06(9, 9)}")

    var m07 = { number1: Int , number2: Int -> number1.toDouble() + number2.toDouble()}
    println("m07:${m07(100, 100)}")

    var m08 : (String, String) -> Unit = {aString, bString -> println("a:$aString,  b:$bString")}
    m08("李元霸", "王五")

    var m09 : (String) -> String = {str -> str}
    println("m09:${m09("降龙十八掌")}")

    var m10 : (Int) -> Unit = {
        when(it) {
            1 -> println("你是一")
            in 20..30 -> println("你是 二十 到 三十")
            else -> println("其他的数字")
        }
    }
    m10(29)

    var m11 : (Int, Int, Int) -> Unit = { n1, n2, n3 ->
        println("n1:$n1, n2:$n2, n3:$n3")
    }
    m11(29,22,33)

    var m12 = { println("我就是m12函数，我就是我") }
    m12()

    val m13 = {sex: Char -> if (sex == 'M') "代表是男的" else "代表是女的"}
    println("m13:${m13('M')}")

    // 覆盖操作
    var m14 = {number: Int -> println("我就是m14  我的值: $number")}
    m14 = {println("覆盖  我的值: $it")}
    m14(99)

    // 需求：我想打印， 并且，我还想返回值，就是 我什么都想要
    var m15 = { number: Int -> println("我想打印: $number")
        number + 1000000
    }
    println("m15:${m15(88)}")










}