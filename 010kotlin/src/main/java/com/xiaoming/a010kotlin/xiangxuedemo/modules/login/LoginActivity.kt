package com.xiaoming.a010kotlin.xiangxuedemo.modules.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.xiaoming.a010kotlin.R
import com.xiaoming.a010kotlin.xiangxuedemo.api.WanAndroidAPI
import com.xiaoming.a010kotlin.xiangxuedemo.config.Flag
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginRegisterResponse
import com.xiaoming.a010kotlin.xiangxuedemo.net.APIClient
import com.xiaoming.a010kotlin.xiangxuedemo.net.APIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.xiaoming.a010kotlin.MainActivity
import com.xiaoming.a010kotlin.xiangxuedemo.base.BaseActivity
import com.xiaoming.a010kotlin.xiangxuedemo.modules.login.interfaces.LoginPresenter
import com.xiaoming.a010kotlin.xiangxuedemo.modules.login.interfaces.LoginView
import com.xiaoming.a010kotlin.xiangxuedemo.modules.register.RegisterActivity

//登录界面
class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        user_login_bt.setOnClickListener(onClickListener)

        user_register_tv.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private val onClickListener = View.OnClickListener { view ->
        // id = 内部根据= 知道你是 要 setId，  否则就是getId
        when(view.id) {
            //登录
            R.id.user_login_bt -> {
                val userNameStr = user_phone_et.text.toString()
                val userPwStr = user_password_et.text.toString()
                Log.d(Flag.TAG, "userName: $userNameStr,  userPwd: $userPwStr")

                //执行 P层
                presenter.loginAction(this@LoginActivity, userNameStr, userPwStr)

            }
        }
    }

    // 响应的两个函数
    override fun loginSuccess(loginBean: LoginRegisterResponse?) {
        Toast.makeText(this@LoginActivity, "登录成功😀", Toast.LENGTH_SHORT).show()

        val intent = Intent(this@LoginActivity,  MainActivity::class.java)
        startActivity(intent)
    }

    override fun loginFailure(erroeMsg: String?) {
        Toast.makeText(this@LoginActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
    }

    override fun createP(): LoginPresenter = LoginPresenterImpl(this)

    override fun recycle() {
        presenter.unAttachView()
    }
}
