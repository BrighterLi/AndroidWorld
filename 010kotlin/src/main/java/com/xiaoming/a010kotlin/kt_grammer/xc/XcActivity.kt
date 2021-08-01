package com.xiaoming.a010kotlin.kt_grammer.xc

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.xiaoming.a010kotlin.R
import kotlinx.android.synthetic.main.activity_xc.*
import kotlinx.coroutines.*

class XcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xc)
    }
    fun click1(view: View) = runBlocking { // 外协程
        // 协程环境了  默认是：main线程
        launch(Dispatchers.IO) { // 内协程
            Log.e("click1: ", "launch ${Thread.currentThread().name}")

            repeat(10) {
                Thread.sleep(1000)
                Log.e("click1: ", "计数中: ${it}")
            }
        }
    }

    fun click2(view: View) = runBlocking {
        // 不适用协程
        // displayMethod(textView)

        // 使用协程
        displayMethodOk(textView)
    }


    fun click3(view: View) = runBlocking {
        // TODO 完成这种  异步线程  和  主线程  的切换，  这个需求：之前我们用RxJava实现过了哦
        // 1.注册耗时操作
        // 2.注册耗时操作完成后，更新注册UI
        // 3.登录耗时操作
        // 4.登录耗时操作完成后，更新登录UI

        // main
        launch {

            val pro = ProgressDialog(this@XcActivity)
            pro.setMessage("正在执行中...")
            pro.show()

            withContext(Dispatchers.IO) {
                // 1.注册耗时操作  异步
                Log.d("click3", "1.注册耗时操作: ${Thread.currentThread().name}")
                Thread.sleep(2000)
            }

            // 2.注册耗时操作完成后，更新注册UI  main
            Log.d("click3", "2.注册耗时操作完成后，更新注册UI: ${Thread.currentThread().name}")

            withContext(Dispatchers.IO) {
                // 3.登录耗时操作  异步
                Log.d("click3", "3.登录耗时操作: ${Thread.currentThread().name}")
                Thread.sleep(3000)
            }

            // 4.登录耗时操作完成后，更新登录UI
            Log.d("click3", "4.登录耗时操作完成后，更新登录UI: ${Thread.currentThread().name}")

            // pro.dismiss()
        }

    }

    // 非阻塞
    fun click4(view: View)  {
        // TODO 完成这种  异步线程  和  主线程  的切换，  这个需求：之前我们用RxJava实现过了哦
        // 1.注册耗时操作
        // 2.注册耗时操作完成后，更新注册UI
        // 3.登录耗时操作
        // 4.登录耗时操作完成后，更新登录UI

        // main
        GlobalScope.launch (Dispatchers.Main) {

            val pro = ProgressDialog(this@XcActivity)
            pro.setMessage("正在执行中...")
            pro.show()

            withContext(Dispatchers.IO) {
                // 1.注册耗时操作  异步
                Log.d("click3", "1.注册耗时操作: ${Thread.currentThread().name}")
                Thread.sleep(2000)
            }

            // 2.注册耗时操作完成后，更新注册UI  main
            Log.d("click3", "2.注册耗时操作完成后，更新注册UI: ${Thread.currentThread().name}")
            textView.text = "注册成功，你可以去登录了"
            pro.setMessage(textView.text.toString())

            withContext(Dispatchers.IO) {
                // 3.登录耗时操作  异步
                Log.d("click3", "3.登录耗时操作: ${Thread.currentThread().name}")
                Thread.sleep(3000)
            }

            // 4.登录耗时操作完成后，更新登录UI
            Log.d("click3", "4.登录耗时操作完成后，更新登录UI: ${Thread.currentThread().name}")
            textView.text = "登录成功，欢迎回来"
            pro.setMessage(textView.text.toString())

            pro.dismiss()
        }

    }
}
