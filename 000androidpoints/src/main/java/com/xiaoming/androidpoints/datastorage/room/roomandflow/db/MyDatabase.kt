package com.xiaoming.androidpoints.datastorage.room.roomandflow.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xiaoming.androidpoints.datastorage.room.roomandflow.dao.UserInfoDao
import com.xiaoming.androidpoints.datastorage.room.roomandflow.entity.UserInfo

/**
 * User: BrightLi
 * Date: 2023-09-04-00:15
 * DESC:
 */

/**
 * entities    数据库里存入的表，可是多个
 * version      数据库的版本号
 * exportSchema    是否生成json文件，用于查看数据库的结构
 */

private const val DB_NAME: String = "my.db"

@Database(entities = [UserInfo::class], version = 1, exportSchema = true)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserInfoDao

    companion object {
        private var instance: MyDatabase? = null

        fun getInstance(): MyDatabase {
            checkNotNull(instance) { "init has not been called" }
            return instance as MyDatabase
        }

        fun init(context: Context) {
            synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    MyDatabase::class.java, DB_NAME
                ).build().let { instance = it }
            }
        }
    }
}