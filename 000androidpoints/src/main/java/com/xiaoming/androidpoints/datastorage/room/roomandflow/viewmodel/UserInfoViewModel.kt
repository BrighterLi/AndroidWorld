package com.xiaoming.androidpoints.datastorage.room.roomandflow.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xiaoming.androidpoints.datastorage.room.roomandflow.dao.UserInfoDao
import com.xiaoming.androidpoints.datastorage.room.roomandflow.db.MyDatabase
import com.xiaoming.androidpoints.datastorage.room.roomandflow.entity.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * User: BrightLi
 * Date: 2023-09-04-00:25
 * DESC:
 */

class UserInfoViewModel : ViewModel() {
    companion object {
        private const val TAG = "UserInfoViewModel"
    }


    val id = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val age = MutableLiveData<String>()
    private val userInfoDao: UserInfoDao by lazy {
        MyDatabase.getInstance().getUserDao()
    }
    fun insert(v: View) {
        Log.d(TAG, "insert id.value: ${id.value} name.value: ${name.value} age.value: ${age.value}")
        if (id.value == null || name.value == null || age.value == null) {
            return
        }
        val userInfo = UserInfo(id.value!!.toInt(), name.value!!, age.value!!.toInt())
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "insert userInfo: $userInfo")
            userInfoDao.insert(userInfo)
        }

    }
    fun getUserInfo(): Flow<List<UserInfo>> {
        return userInfoDao
            .getUserInfoList()
            .flowOn(Dispatchers.IO)
    }
}