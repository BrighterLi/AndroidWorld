package com.xiaoming.androidpoints.datastorage.room.roomandflow.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

@Database(entities = [UserInfo::class], version = 2, exportSchema = true)
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
                ).addMigrations(MIGRATION_1_2)
                    .build().let { instance = it }
            }
        }

        // MIGRATION
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE UserInfo ADD COLUMN city INTEGER  NOT NULL DEFAULT 10")
            }
        }
    }
}