package com.xiaoming.androidpoints.datastorage.cp

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle

class UserContentProvider : ContentProvider(){
    companion object{
        /**
         * 用来初始化matcher
         */
        class Matcher(){
            fun get_matcher(AUTHORITY:String, NAME:Int, Names:Int) : UriMatcher{
                val matcher:UriMatcher = UriMatcher(UriMatcher.NO_MATCH)
                matcher.addURI(AUTHORITY, "names", Names)
                matcher.addURI(AUTHORITY, "name/#", NAME)
                return matcher
            }
        }
        val NAME:Int  = 2
        val NAMES:Int = 1
        var NAME_TABLE = "user" //表名
        val AUTHORITY:String = "com.example.iplayer.testprovider"//包名
        val _ID:String = "id"
        var helperDB:OpenHelperDB? = null
        val matcher = Matcher().get_matcher(AUTHORITY, NAME, NAMES) // 定义Uri匹配器
    }


    /**
     * 插入操作
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db:SQLiteDatabase? = helperDB?.readableDatabase
        when(matcher.match(uri)){
            NAMES -> {
                val rowId:Long? = db?.insert(NAME_TABLE, null, values)
                if (rowId != null) {
                    if(rowId > 0){
                        val uri1:Uri = ContentUris.withAppendedId(uri, rowId)
                        context?.contentResolver?.notifyChange(uri1, null)
                        return uri
                    }
                }

            }
            else -> throw IllegalArgumentException("Unrecognized Uri !")
        }
        return null
    }

    /**
     * 查询操作
     */
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db:SQLiteDatabase? = helperDB?.readableDatabase
        when(matcher.match(uri)){
            NAMES -> return db?.query(NAME_TABLE, null, null, null, null, null, null)
            NAME -> return db?.query(NAME_TABLE, null, "id = ?", selectionArgs, null ,null, null)
            else -> throw IllegalArgumentException("Unrecognized Uri !")
        }

    }

    /**
     * 判断是否已经创建
     */
    override fun onCreate(): Boolean {
        helperDB = OpenHelperDB(context, "test.db", 1)
        return true
    }

    /**
     * 更新操作
     */
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val db:SQLiteDatabase? = helperDB?.readableDatabase
        var num:Int? = 0
        when(matcher.match(uri)){
            NAMES -> num = db?.update(NAME_TABLE, values, selection, selectionArgs)
            else -> throw IllegalArgumentException("Unrecognized Uri !")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return num ?: 0
    }

    /**
     * 删除操作
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db:SQLiteDatabase? = helperDB?.readableDatabase
        var num:Int? = 0
        when(matcher.match(uri)){
            NAMES -> num = db?.delete(NAME_TABLE, null, null)
            NAME -> {
                val id:Long = ContentUris.parseId(uri)
                val whereClause:String = "$_ID=$id"
                num = db?.delete(NAME_TABLE, whereClause, selectionArgs)
            }
            else -> throw IllegalArgumentException("Unrecognized Uri !")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return num ?: 0
    }

    /**
     * 获取类型
     */
    override fun getType(uri: Uri): String? {
        return null
    }

    /**
     * 跨进程调用
     */
    override fun call(method: String, arg: String?, extras: Bundle?): Bundle? {
        return super.call(method, arg, extras)
    }
}
