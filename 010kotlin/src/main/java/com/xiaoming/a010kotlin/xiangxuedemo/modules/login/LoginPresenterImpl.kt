package com.xiaoming.a010kotlin.xiangxuedemo.modules.login

import android.content.Context
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginRegisterResponse
import com.xiaoming.a010kotlin.xiangxuedemo.modules.login.interfaces.LoginModule
import com.xiaoming.a010kotlin.xiangxuedemo.modules.login.interfaces.LoginPresenter
import com.xiaoming.a010kotlin.xiangxuedemo.modules.login.interfaces.LoginView

// Presenter层的实现
class LoginPresenterImpl (var loginView: LoginView?) : LoginPresenter, LoginPresenter.OnLoginListener {

    // 需要 Model层 去请求服务器
    private val loginModel: LoginModelImpl = LoginModelImpl()

    // 需要 View层 去更新 Activity/Fragment
    override fun loginAction(context: Context, username: String, password: String) {
        // TODO
        // 可以做很多的事情
        // 可以省略很多代码
        // 很多的校验
        // ....
        loginModel.login(context, username, password, this)
    }

    // Base Presenter 的
    override fun unAttachView() {
        loginView = null
        loginModel.cancelRequest() // 取消请求
    }

    // 接收 Model 的结果集
    override fun loginSuccess(loginBean: LoginRegisterResponse?) {
        // TODO
        // 可以做很多的事情
        // 可以省略很多代码
        // 很多的校验
        // ....
        loginView?.loginSuccess(loginBean)
    }

    override fun loginFialure(erroeMsg: String?) {
        // TODO
        // 可以做很多的事情
        // 可以省略很多代码
        // 很多的校验
        // ....

        loginView?.loginFailure(erroeMsg)
    }


}