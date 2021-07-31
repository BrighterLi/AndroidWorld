package com.xiaoming.a010kotlin.xiangxuedemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// java  entities = {}
// kt entities = []
@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    // 最终给用户的就是 DAO
    abstract fun getStudentDao() : StudentDao

    companion object {

        private var INSTANCE : StudentDatabase ? = null

        // Application
        fun getDatabase(context: Context) : StudentDatabase ? {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, StudentDatabase::class.java, "student_database.db")
                        .allowMainThreadQueries() // 允许在主线程运行
                        .build()
            }

            return INSTANCE

        }

        fun getDatabase() : StudentDatabase? = INSTANCE

    }

}