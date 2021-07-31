package com.xiaoming.a010kotlin.xiangxuedemo.modules.collect

import com.xiaoming.a010kotlin.xiangxuedemo.database.Student
import com.xiaoming.a010kotlin.xiangxuedemo.modules.collect.interfaces.CollectModel
import com.xiaoming.a010kotlin.xiangxuedemo.modules.collect.interfaces.CollectPresenter
import com.xiaoming.a010kotlin.xiangxuedemo.modules.collect.interfaces.CollectView

// P impl
class CollectPresenterImpl(var view: CollectView?) : CollectPresenter
        ,
        CollectPresenter.OnCollectListener, CollectPresenter.OnCollectResponseListener

{

    private val modele: CollectModel = CollectModuleImpl()

    // 插入
    override fun requestInsert(vararg students: Student) {
        modele.requestInsert(this, *students)
    }

    // 条件修改
    override fun requestUpdate(vararg students: Student) {
        modele.requestUpdate(this, *students)
    }

    // 添加删除
    override fun requestDelete(vararg students: Student) {
        modele.requestDelete(this, *students)
    }

    // 删除全部
    override fun requestDeleteAll() {
        modele.requestDeleteAll(this)
    }

    // 查询全部
    override fun requestQueryAll() {
        modele.requestQueryAll(this)
    }

    override fun unAttachView() {
        view = null
    }

    // 结果

    override fun showResultSuccess(result: List<Student>?) {
        view?.showResultSuccess(result)
    }

    override fun showIUD(iudResult: Boolean) {
        view?.showResult(iudResult)
    }

}