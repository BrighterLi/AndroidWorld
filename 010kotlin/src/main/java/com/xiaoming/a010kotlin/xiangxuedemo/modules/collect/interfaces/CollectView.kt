package com.xiaoming.a010kotlin.xiangxuedemo.modules.collect.interfaces

import com.xiaoming.a010kotlin.xiangxuedemo.database.Student

interface CollectView {

    // 显示数据  --- 》 View

    fun showResultSuccess(result: List<Student> ?)

    fun showResult(result: Boolean)
}