package com.xiaoming.a010kotlin.kt_grammer.xc

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import android.widget.TextView

private val mOkHttpClient: OkHttpClient = OkHttpClient()
private val mRequest = Request.Builder().url("https://baidu.com").get().build()

// TODO 同学们，这个是不使用协程的   是使用传统写法
fun displayMethod(textView: TextView) {

    // 主线程 更新UI
    val han = Handler(Looper.getMainLooper()) {
        textView.text = it.obj as String

        false
    }

    // 异步线程
    object: Thread() {
        override fun run() {
            super.run()

            // Thread.sleep(2000)

            // 不考虑异常的情况
            val result = mOkHttpClient.newCall(mRequest).execute().body()?.string()
            val msg =  han.obtainMessage()
            msg.obj = result
            han.sendMessage(msg)
        }
    }.start()



}

// =====================
fun displayMethodOk(textView: TextView) = runBlocking {
    /*launch {
        // main

        // 默认还是 main
        // Dispatchers.IO  异步
        val def = async(Dispatchers.IO) {

            // 异步线程

            true
            "String"

            // 不考虑异常的情况
            mOkHttpClient.newCall(mRequest).execute().body()?.string()
        }

        // main
        // 可以拿到异步执行后的结果
        textView.text = def.await()
    }*/

    // 下面是简化
    launch {
        textView.text = async(Dispatchers.IO) {
            mOkHttpClient.newCall(mRequest).execute().body()?.string()  // 异步的
        }.await()
    }
}