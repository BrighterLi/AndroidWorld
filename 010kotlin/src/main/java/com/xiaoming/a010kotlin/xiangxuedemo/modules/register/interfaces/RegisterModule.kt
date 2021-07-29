package com.xiaoming.a010kotlin.xiangxuedemo.modules.register.interfaces

import android.content.Context

interface RegisterModule {

    fun cancelRequest()

    fun register(
            context: Context,
            username: String,
            password: String,
            repassword: String,

            // 把结果 给 P层  接口回调
            callback: RegisterPersenter.OnRegisterListener
    )
}