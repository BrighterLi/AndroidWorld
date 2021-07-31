package com.xiaoming.a010kotlin.xiangxuedemo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student() {

    // 主键，自动增长
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "name") // 可以加别名，优先级更高， 如果加了 就使用别名的名称
    lateinit var name: String

    @ColumnInfo(name = "age") // 可以加别名，优先级更高， 如果加了 就使用别名的名称
    var age: Int = 0

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }
}