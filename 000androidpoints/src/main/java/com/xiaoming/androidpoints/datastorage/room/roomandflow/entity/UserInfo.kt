package com.xiaoming.androidpoints.datastorage.room.roomandflow.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * User: BrightLi
 * Date: 2023-09-04-00:14
 * DESC:
 */

//Room实体类声明
@Entity
data class UserInfo(
    @PrimaryKey val id: Int,
    var userName: String,
    var age: Int
)