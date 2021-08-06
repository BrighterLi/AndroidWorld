package com.xiaoming.a010kotlin

import android.app.Application
import android.util.Log
import com.xiaoming.a010kotlin.xiangxuedemo.config.Flag
import com.xiaoming.a010kotlin.xiangxuedemo.database.StudentDatabase

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(Flag.TAG, "MyApplication onCreate run")

        // 初始化
        //StudentDatabase.getDatabase(this)
    }

}