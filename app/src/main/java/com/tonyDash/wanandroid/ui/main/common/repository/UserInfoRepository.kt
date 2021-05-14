package com.tonyDash.wanandroid.ui.main.common.repository

import com.tonyDash.wanandroid.room.dao.UserDao
import com.tonyDash.wanandroid.ui.main.mine.model.UserInfo

class UserInfoRepository(private val dao: UserDao) {

    suspend fun saveLocalUser(user: UserInfo) = dao.insertAll(user)

    suspend fun getLocalUsers(): List<UserInfo> = dao.getAll()

    suspend fun deleteLocalUser(user: UserInfo) = dao.deleteAll(user)

    suspend fun isLogin():Boolean {
        return !getLocalUsers().isNullOrEmpty()
    }
}