package com.xiaoming.a010kotlin.xiangxuedemo.base


//所有P层的最上层父类“  最上层标准

interface IBasePresenter {

    // 试图离开了 （Activity， Fragment）  离开了   销毁的事情
    fun unAttachView()
}