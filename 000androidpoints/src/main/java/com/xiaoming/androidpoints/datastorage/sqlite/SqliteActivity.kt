package com.xiaoming.androidpoints.datastorage.sqlite

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaoming.androidpoints.R

class SqliteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        createDb()
    }

    /**
     * 创建数据库
     * @param
     * @return null
     */
    private fun createDb() {
        val db = SQLiteDatabase.openOrCreateDatabase(
            "/data/data/$packageName/test.db",
            null)
    }
}