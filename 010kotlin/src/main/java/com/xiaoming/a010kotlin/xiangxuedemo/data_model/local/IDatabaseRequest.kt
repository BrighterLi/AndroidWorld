package com.xiaoming.a010kotlin.xiangxuedemo.data_model.local

import com.xiaoming.a010kotlin.xiangxuedemo.database.Student

/**
 * 为了扩展，这样写（在仓库里面的）
 * 数据库获取标准接口（在仓库里面） 也就是数据库的数据读取（包括数据库数据，等）
 * 只为 LocalRoomRequestManager 服务
 *
 * DB 数据
 */
interface IDatabaseRequest {

    fun insertStudents(vararg students: Student)

    fun updateStudents(vararg students: Student)

    fun deleteStudents(vararg students: Student)

    fun deleteAllStudent()

    fun queryAllStudent() : List<Student> ?

    // TODO 可扩展 ...


}