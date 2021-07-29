package com.xiaoming.a010kotlin.xiangxuedemo.net

import android.content.Context
import io.reactivex.Observer
import com.xiaoming.a010kotlin.xiangxuedemo.entity.LoginRegisterResponseWrapper
import com.xiaoming.a010kotlin.xiangxuedemo.widgets.LoadingDialog
import io.reactivex.disposables.Disposable

// RxJava 自定义 操作符 简单的
// 拦截 自定义操作符，目的: 包装Bean 给拆成两份  如果成功  data--》UI     如果失败  meg--》UI
abstract class APIResponse<T>(val context: Context) //主构造
    : Observer<LoginRegisterResponseWrapper<T>> {
    private var isShow: Boolean = true

    //次构造
    constructor(context: Context, isShow: Boolean = false) : this(context) {
        this.isShow = isShow
    }

    //成功怎么办
    abstract fun success(data: T?)

    //失败怎么办
    abstract fun failure(errorMsg: String?)

    // 启点分发的时候
    override fun onSubscribe(d: Disposable) {
        //弹出 加载框
        if(isShow) {
            LoadingDialog.show(context)
        }
    }

    // 上游流下了的数据
    override fun onNext(t: LoginRegisterResponseWrapper<T>) {
        if(t.data == null) {
            //失败
            failure("登录失败了，请检查原有： msg: ${t.errorMsg}")
        } else {
            //成功
            success(t.data)
        }
    }

    // 上游流下了的错误
    override fun onError(e: Throwable) {
        //取消加载
        LoadingDialog.cancel()
        failure(e.message)
    }

    //停止
    override fun onComplete() {
        //取消加载
        LoadingDialog.cancel()
    }
}