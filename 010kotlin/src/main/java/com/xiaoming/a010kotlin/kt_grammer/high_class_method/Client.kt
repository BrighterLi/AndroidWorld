package com.xiaoming.a010kotlin.kt_grammer.high_class_method

//高阶函数
class Client {
    fun main() {
        show(true) {
            'M'  //这个就是loginMethod具体实现的内容
        }
    }
}

// TODO  loginMethod:(Boolean)->Unit
fun loginMethod(b:Boolean): Unit{

}

// loginMethod 方法名
// (Boolean)方法的那个括号
// -> 方法体 干活
// Unit == void
fun show(isLogin: Boolean, loginMethod: (Boolean)->Char) {
    if(isLogin) {
        println("登录成功")
        val r = loginMethod(true)
        println(r)
    } else{
        println("登录失败")
        loginMethod(false)
    }
}