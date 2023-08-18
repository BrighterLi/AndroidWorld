package com.xiaoming.androidpoints.datastorage.room.demo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xiaoming.androidpoints.datastorage.room.demo.dao.StudentDao

//每次实体做了修改这个version就要升级，会触发数据库迁移的操作
@Database(entities = [Student::class],version = 1)
abstract class MyDb constructor(): RoomDatabase(){
    abstract fun getStudentDao():StudentDao
}
