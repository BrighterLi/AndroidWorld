package com.xiaoming.androidpoints.datastorage.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * User: BrightLi
 * Date: 2023-08-30-01:25
 * DESC:
 */
class MySqliteHelper(context: Context?) :
    SQLiteOpenHelper(context, "mysqlite.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        /* 数据库创建的时候会回调此方法，可以用db对象执行SQL语
   * 句，创建所需要的表
   */
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//当数据库升级时调用此方法
    }
}