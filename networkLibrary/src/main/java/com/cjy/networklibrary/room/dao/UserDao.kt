package com.cjy.networklibrary.room.dao

import androidx.room.*
import com.cjy.networklibrary.entity.UserInfo

@Dao
interface UserDao {

    @Query("select * from userinfo")
    suspend fun getAll(): List<UserInfo>

    @Insert
    suspend fun insertAll(vararg user: UserInfo)

    @Delete
    suspend fun deleteAll(vararg user: UserInfo)

    @Update
    suspend fun updateUser(user:UserInfo)
}