package com.xiaoming.a010kotlin.xiangxuedemo.modules.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.xiaoming.a010kotlin.R
import com.xiaoming.a010kotlin.xiangxuedemo.api.WanAndroidAPI
import com.xiaoming.a010kotlin.xiangxuedemo.config.Flag
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginResponse
import com.xiaoming.a010kotlin.xiangxuedemo.net.APIClient
import com.xiaoming.a010kotlin.xiangxuedemo.net.APIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginResponseWrapper

//登录界面
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        user_login_bt.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener { view ->
        // id = 内部根据= 知道你是 要 setId，  否则就是getId
        when(view.id) {
            //登录
            R.id.user_login_bt -> {
                val userNameStr = user_phone_et.text.toString()
                val userPwStr = user_password_et.text.toString()
                Log.d(Flag.TAG, "userName: $userNameStr,  userPwd: $userPwStr")

                APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                        //RxJava
                        .loginAction(userNameStr, userPwStr) // 起点  往下流  ”包装Bean“
                        .subscribeOn(Schedulers.io())  // 给上面请求服务器的操作，分配异步线程
                        .observeOn(AndroidSchedulers.mainThread())  // 给下面更新UI操作，分配main线程

                       /* .subscribe(object : Consume<LoginResponseWrapper<LoginResponse>> {
                            override fun accet(t: LoginResponseWrapper<LoginResponse>?) {
                                // 我这里是更新UI，拿到了包装Bean，实际上我不需要包装Bean
                            }
                        })*/

                        //拆卸包装的返回的数据，只取自己需要的那部分数据，比如成功时的成功data，失败时的msg
                        .subscribe(object : APIResponse<LoginResponse>(this) {
                            override fun success(data: LoginResponse?) {
                                //成功 data UI
                                Log.e(Flag.TAG, "success: $data")
                                Toast.makeText(this@LoginActivity, "登录成功\uD83D\uDE00", Toast.LENGTH_SHORT).show()
                            }

                            override fun failuer(errorMsg: String?) {
                                //失败 msg UI
                                Log.e(Flag.TAG, "failure: errorMsg:$errorMsg")
                                Toast.makeText(this@LoginActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
                            }
                        })


            }
        }
    }
}
