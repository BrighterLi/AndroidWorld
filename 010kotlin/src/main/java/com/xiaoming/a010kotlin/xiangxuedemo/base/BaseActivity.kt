package com.xiaoming.a010kotlin.xiangxuedemo.base

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P> : AppCompatActivity() where P : IBasePresenter {

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = createP()
    }

    abstract fun createP(): P

    abstract fun recycle()

    override fun onDestroy() {
        super.onDestroy()
        recycle()
    }


    //类似于工具类函数
    //暴露给自己的孩子，隐藏ActionBar
    fun hideActionBar() {
        //任何 Java代码东西，必须用？ 允许为null，来接收
        val actionBar : ActionBar? = supportActionBar
        actionBar?.hide()
    }

    //暴露给自己的孩子，显示ActionBar
    fun showAcionBar() {
        supportActionBar?.show()
    }
}