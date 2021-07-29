package com.xiaoming.a010kotlin.xiangxuedemo.modules.register

import android.content.Context
import com.xiaoming.a010kotlin.xiangxuedemo.api.WanAndroidAPI
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginRegisterResponse
import com.xiaoming.a010kotlin.xiangxuedemo.modules.register.interfaces.RegisterModule
import com.xiaoming.a010kotlin.xiangxuedemo.modules.register.interfaces.RegisterPersenter
import com.xiaoming.a010kotlin.xiangxuedemo.net.APIClient
import com.xiaoming.a010kotlin.xiangxuedemo.net.APIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterModuleImpl : RegisterModule {

    // 取消请求
    override fun cancelRequest() {}

    override fun register(
            context: Context,
            username: String,
            password: String,
            repassword: String,
            callback: RegisterPersenter.OnRegisterListener
    ) {
        // 网络模型
        APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                .registerAction(username, password, repassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : APIResponse<LoginRegisterResponse>(context) {
                    override fun success(data: LoginRegisterResponse?) {
                        callback.registerSuccess(data)
                    }

                    override fun failure(errorMsg: String?) {
                        callback.registerFailed(errorMsg)
                    }

                })
    }
}