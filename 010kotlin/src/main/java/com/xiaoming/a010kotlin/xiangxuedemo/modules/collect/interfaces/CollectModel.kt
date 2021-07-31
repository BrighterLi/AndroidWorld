package com.xiaoming.a010kotlin.xiangxuedemo.modules.collect.interfaces

import com.xiaoming.a010kotlin.xiangxuedemo.database.Student

interface CollectModel {

    // ← 请求去
    fun requestInsert(listener: CollectPresenter.OnCollectListener, vararg students: Student)
    fun requestUpdate(listener: CollectPresenter.OnCollectListener, vararg students: Student)
    fun requestDelete(listener: CollectPresenter.OnCollectListener, vararg students: Student)
    fun requestDeleteAll(listener: CollectPresenter.OnCollectListener) // 删除全部

    fun requestQueryAll(listener: CollectPresenter.OnCollectResponseListener) // 查询全部
}