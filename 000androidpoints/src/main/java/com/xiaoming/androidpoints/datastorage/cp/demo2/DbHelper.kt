package com.xiaoming.androidpoints.datastorage.cp.demo2

/**
 * User: BrightLi
 * Date: 2023-08-29-22:22
 * DESC:
 */
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * 数据库辅助操作类
 */
class DbHelper
    (context: Context?, databaseName: String, version: Int) : SQLiteOpenHelper(
    context, databaseName, null, version
) {
    //创建用户表的sql语句，表名：user_table，包含主键_id,用户名字段：user_name。
    private var createUserTableSql =
        ("CREATE TABLE user_table(_id INTEGER PRIMARY KEY, user_name text)")

    /**
     * 创建数据库表
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createUserTableSql)
    }

    /**
     * 数据库升级
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS user_table"
        db.execSQL(sql)
        onCreate(db)
    }

    /**
     * 插入数据
     */
    fun insert(tableName: String, cv: ContentValues) = writableDatabase.insert(tableName, null, cv)

    /**
     * 删除指定数据
     */
    fun delete(tableName: String, id: Int): Int {
        val where = "_id = ?"
        val whereValue = arrayOf(id.toString())
        return writableDatabase.delete(tableName, where, whereValue)
    }

    /**
     * 删除所有数据
     */
    fun deleteAll(tableName: String) = writableDatabase.delete(tableName, null, null)

    /**
     * 更新数据
     */
    fun update(
        tableName: String, values: ContentValues, whereClause: String?, whereArgs: Array<String>?
    ) = writableDatabase.update(tableName, values, whereClause, whereArgs)

    /**
     * 查询数据，返回一个指定数据集合的游标
     */
    fun query(tableName: String): Cursor {
        return readableDatabase.query(tableName, null, null, null, null, null, null)
    }
}