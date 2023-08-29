package com.xiaoming.androidpoints.datastorage.cp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class OpenHelperDB(context:Context?, baseName:String, version:Int) : SQLiteOpenHelper(context, baseName, null, version) {
    //我们的表的创建语句
    val BASE_TABLE:String = "CREATE TABLE user(" +
            "id integer primary key autoincrement not null," +
            "name varchar(30) not null);"

    /**
     * 创建执行函数
     */
    override fun onCreate(db: SQLiteDatabase?) {
        //创建我们的表
        db?.execSQL(BASE_TABLE)
    }

    /**
     * 更新执行函数
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}