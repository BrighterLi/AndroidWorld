package com.xiaoming.a010kotlin.xiangxuedemo.modules.login.interfaces

import android.content.Context
import com.xiaoming.a010kotlin.xiangxuedemo.base.IBasePresenter
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginRegisterResponse

// Presenter层，控制器，主要做检验工作
interface LoginPresenter : IBasePresenter{

    //登录
    fun loginAction(context: Context, username: String, password: String)

    //监听回调
    interface OnLoginListener {
        fun loginSuccess(loginBean: LoginRegisterResponse?)

        fun loginFialure(erroeMsg: String  ?)
    }
}