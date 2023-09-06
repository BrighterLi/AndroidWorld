package com.xiaoming.androidpoints.datastorage.room.roomandflow.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xiaoming.androidpoints.datastorage.room.roomandflow.entity.UserInfo
import kotlinx.coroutines.flow.Flow

/**
 * User: BrightLi
 * Date: 2023-09-04-00:16
 * DESC:
 */
@Dao
interface UserInfoDao {
    //id重复的替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userInfo: UserInfo)

    //返回Flow，由于Flow需要使用collect，该函数为挂起函数，所以不需要加suspend了
    @Query("SELECT * FROM userinfo")
    fun getUserInfoList(): Flow<List<UserInfo>>
}