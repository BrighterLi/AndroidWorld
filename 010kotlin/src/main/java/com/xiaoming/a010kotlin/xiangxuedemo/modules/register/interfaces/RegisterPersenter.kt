package com.xiaoming.a010kotlin.xiangxuedemo.modules.register.interfaces

import android.content.Context
import com.xiaoming.a010kotlin.xiangxuedemo.base.IBasePresenter
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginRegisterResponse

interface RegisterPersenter : IBasePresenter {

    fun registerWanAndroid(context: Context, username: String, password: String, repassword: String)

    // M  ---》 P  接口监听
    interface OnRegisterListener {

        fun registerSuccess(registerBean: LoginRegisterResponse?)

        fun registerFailed(errorMsg: String?)

    }

}