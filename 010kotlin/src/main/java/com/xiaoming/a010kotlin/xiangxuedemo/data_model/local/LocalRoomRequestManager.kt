package com.xiaoming.a010kotlin.xiangxuedemo.data_model.local

import com.xiaoming.a010kotlin.xiangxuedemo.database.Student
import com.xiaoming.a010kotlin.xiangxuedemo.database.StudentDao
import com.xiaoming.a010kotlin.xiangxuedemo.database.StudentDatabase

// 仓库角色 本地/DB
class LocalRoomRequestManager : ILocalRequest, IDatabaseRequest {

    // TODO 可扩展 ...

    /** TODO ********************** 下面这一系列都是 本地相关的 ************************/

    var studentDao: StudentDao? = null

    // Java构造代码块  dao
    init {
        val studentDatabase: StudentDatabase? = StudentDatabase.getDatabase()
        studentDao = studentDatabase?.getStudentDao()
    }

    // 单例模式而已
    companion object {
        var INSTANCE: LocalRoomRequestManager? = null

        fun getInstance() : LocalRoomRequestManager {
            if (INSTANCE == null) {
                synchronized(LocalRoomRequestManager::class) {
                    if (INSTANCE == null) {
                        INSTANCE = LocalRoomRequestManager()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    // TODO 增加
    override fun insertStudents(vararg students: Student) {
        studentDao?.insertStudents(*students)
    }

    // TODO 更新
    override fun updateStudents(vararg students: Student) {
        studentDao?.updateStudents(*students)
    }

    // TODO 删除
    override fun deleteStudents(vararg students: Student) {
        studentDao?.deleteStudents(*students)
    }

    // TODO 全部删除
    override fun deleteAllStudent() {
        studentDao?.deleteAllStudents()
    }

    // TODO 全部查询
    override fun queryAllStudent() : List<Student>? {
        return studentDao?.queryAllStudents()
    }







    /*// TODO 增加
    inner class InsertAsyncTask : AsyncTask<Student, Void, Void>() {
        override fun doInBackground(vararg params: Student): Void? {
            studentDao?.insertStudents(*params)
            return null
        }
    }

    // TODO 更新
    inner class UpdateAsyncTask : AsyncTask<Student, Void, Void>() {
        override fun doInBackground(vararg params: Student): Void? {
            studentDao?.updateStudents(*params)
            return null
        }
    }

    // TODO 删除
    inner class DeleteAsyncTask : AsyncTask<Student, Void, Void>() {
        override fun doInBackground(vararg params: Student): Void? {
            studentDao?.deleteStudents(*params)
            return null
        }
    }

    // TODO 全部删除
    inner class AllDeleteAsyncTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            studentDao?.deleteAllStudents()
            return null
        }
    }

    // TODO 全部查询
    inner class AllQueryAsyncTask : AsyncTask<Void, Void, List<Student>>() {
        override fun doInBackground(vararg params: Void?): List<Student> {
            return studentDao?.queryAllStudents()!!
        }
    }*/
}