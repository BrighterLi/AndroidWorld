package com.xiaoming.a010kotlin.xiangxuedemo.modules.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaoming.a010kotlin.R
import com.xiaoming.a010kotlin.xiangxuedemo.base.BaseActivity
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginRegisterResponse
import com.xiaoming.a010kotlin.xiangxuedemo.modules.register.interfaces.RegisterPersenter
import com.xiaoming.a010kotlin.xiangxuedemo.modules.register.interfaces.RegisterView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import android.widget.Toast

class RegisterActivity : BaseActivity<RegisterPersenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideActionBar()

        setContentView(R.layout.activity_register)

        user_register_bt.setOnClickListener {
            //调用注册
            val usernameStr = user_phone_et2.text.toString()
            val passwordStr = user_password_et2.text.toString()
            presenter.registerWanAndroid(this@RegisterActivity, usernameStr, passwordStr, passwordStr)
        }
    }

    override fun createP(): RegisterPersenter = RegisterPresenterImpl(this)

    override fun recycle() {
        presenter.unAttachView()
    }

    override fun registerSuccess(registerBean: LoginRegisterResponse?) {
        Toast.makeText(this, "注册成功😀", Toast.LENGTH_SHORT).show()
    }

    override fun registerFailed(errorMsg: String?) {
        Toast.makeText(this, "注册失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
    }
}
