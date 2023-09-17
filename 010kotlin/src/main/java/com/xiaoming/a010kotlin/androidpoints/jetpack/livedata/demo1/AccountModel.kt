package com.xiaoming.a010kotlin.androidpoints.jetpack.livedata.demo1

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * User: BrightLi
 * Date: 2023-07-02-23:51
 * DESC:
 */
class AccountModel(@NonNull application: Application?) : AndroidViewModel(
    application!!
) {
    // 创建LiveData
    private val mAccount: MutableLiveData<AccountBean> = MutableLiveData()
    fun setAccount(name: String?, phone: String?, blog: String?) {
        mAccount.setValue(AccountBean(name, phone, blog))
    }

    fun getAccount(): MutableLiveData<AccountBean> {
        return mAccount
    }

    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared()
    override fun onCleared() {
        Log.e("AccountModel", "==========onCleared()==========")
        super.onCleared()
    }
}