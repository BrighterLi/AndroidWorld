package com.xiaoming.a010kotlin.kt_grammer.high_class_method

fun main() {
    // 用户
    // 再次模拟登录流程
    loginEngine("Derry", "123456") {
        if (it) println("最终得到的结果是 登录成功") else println("最终得到的结果是 登录失败")
    }

    // 有返回值
    val r = loginTest() {
        true
    }
    println("方法的结果：${r}")
}

/*public fun login(userName: String, userPwd: String,      responseResult: (Boolean)-> Unit) {
    loginEngine(userName, userPwd, responseResult)
}*/

// 内部去完成登录功能
private fun loginEngine(userName: String, userPwd: String,   responseResult: (Boolean)-> Unit) {
    val DB_USER_NAME = "Derry"
    val DB_USER_PWD = "123456"

    if (userName == DB_USER_NAME && userPwd == DB_USER_PWD) {
        // TODO 模拟做了很多业务逻辑
        //  ......
        responseResult(true)
    } else {
        // TODO 模拟做了很多业务逻辑
        //  ......
        responseResult(false)
    }
}

fun loginTest(mm: ()-> Boolean) : Int {
    val result = mm()
    println("result:$result")

    return 99999
}

