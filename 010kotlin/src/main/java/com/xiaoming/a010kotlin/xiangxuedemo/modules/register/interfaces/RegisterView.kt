package com.xiaoming.a010kotlin.xiangxuedemo.modules.register.interfaces

import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginRegisterResponse

interface RegisterView {

    // 成功 失败 显示 到 Activty

    fun registerSuccess(registerBean: LoginRegisterResponse?)

    fun registerFailed(errorMsg: String?)
}