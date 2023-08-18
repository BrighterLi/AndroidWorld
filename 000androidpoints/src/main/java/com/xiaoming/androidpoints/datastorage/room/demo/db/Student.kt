package com.xiaoming.androidpoints.datastorage.room.demo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "student_name")
    var name: String? = "",
    @ColumnInfo(name = "student_age")
    var age: Int? = 0
) {
    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }
}

