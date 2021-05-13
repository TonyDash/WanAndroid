package com.tonyDash.wanandroid.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tonyDash.wanandroid.ui.main.mine.model.UserInfo

@Dao
interface UserDao {

    @Query("select * from userinfo")
    suspend fun getAll(): List<UserInfo>

    @Insert
    suspend fun insertAll(vararg user: UserInfo)

    @Delete
    suspend fun deleteAll(vararg user: UserInfo)

}