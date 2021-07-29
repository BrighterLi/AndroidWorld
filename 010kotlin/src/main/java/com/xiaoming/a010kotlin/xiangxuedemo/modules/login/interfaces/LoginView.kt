package com.xiaoming.a010kotlin.xiangxuedemo.modules.login.interfaces

import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginRegisterResponse

//View层:显示
interface LoginView {
    //把结果显示到Activity/Fragment
    fun loginSuccess(loginBean: LoginRegisterResponse?)

    fun loginFailure(errorMsg: String ?)
}