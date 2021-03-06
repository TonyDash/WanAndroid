package com.tonyDash.wanandroid.ui.main.common.repository

import com.cjy.networklibrary.entity.UserInfo
import com.cjy.networklibrary.room.dao.UserDao

class UserInfoRepository(private val dao: UserDao) {

    suspend fun saveLocalUser(user: UserInfo) = dao.insertAll(user)

    suspend fun getLocalUsers(): List<UserInfo> = dao.getAll()

    suspend fun deleteLocalUser(user: UserInfo) = dao.deleteAll(user)

    suspend fun updateLocalUser(user: UserInfo) = dao.updateUser(user)
}