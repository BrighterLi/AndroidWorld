package com.xiaoming.androidpoints.datastorage.room.demo.dao

import androidx.room.*
import com.xiaoming.androidpoints.datastorage.room.demo.db.Student

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("select * from table_student where id = :id")
    suspend fun getStudent(id:Int):Student?

    @Query("select * from table_student where student_name like :name")
    suspend fun getStudents(name:String):List<Student>

    @Query("select * from table_student")
    suspend fun getAllStudents():List<Student>
}