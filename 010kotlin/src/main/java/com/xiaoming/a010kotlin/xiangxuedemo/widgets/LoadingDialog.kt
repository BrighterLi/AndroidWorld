package com.xiaoming.a010kotlin.xiangxuedemo.widgets

import android.app.Dialog
import android.content.Context
import com.xiaoming.a010kotlin.R

// 加载框
// object 没有主构造 也没有次构造
object LoadingDialog {
    // 内部生成的时候 根据INSTANCE  看起来感觉是 静态 因为可以 LoadingDialog.show
    fun show1() { }

    // 真正的static
    @JvmStatic
    fun show2() {}

    private var dialog: Dialog ? = null

    fun show(context: Context) {
        cancel()

        dialog = Dialog(context)
        dialog?.setContentView(R.layout.dialog_loading)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        // .....
        dialog?.show()
    }

    fun cancel() {
        dialog?.dismiss()
    }

}