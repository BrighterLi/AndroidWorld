package com.xiaoming.a010kotlin.xiangxuedemo.modules.collect.interfaces

import com.xiaoming.a010kotlin.xiangxuedemo.base.IBasePresenter
import com.xiaoming.a010kotlin.xiangxuedemo.database.Student

interface CollectPresenter : IBasePresenter {

    // ← 请求去
    fun requestInsert(vararg students: Student)
    fun requestUpdate(vararg students: Student)
    fun requestDelete(vararg students: Student)
    fun requestDeleteAll() // 删除全部
    fun requestQueryAll() // 查询全部

    // → 回来处
    interface OnCollectResponseListener {
        fun showResultSuccess(result: List<Student> ?)
    }
    // → 回来处
    interface OnCollectListener {
        fun showIUD(iudResult: Boolean)
    }
}