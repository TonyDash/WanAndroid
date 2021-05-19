package com.cjy.webviewlibrary.repository

import com.cjy.commonlibrary.store.UserInfoStore
import com.cjy.networklibrary.client.RetrofitClient
import com.cjy.networklibrary.entity.UserInfo
import com.cjy.networklibrary.room.dao.UserDao
import com.cjy.networklibrary.room.userDao

class UserInfoRepository() {

    private val dao: UserDao = userDao

    suspend fun saveLocalUser(user: UserInfo) = dao.insertAll(user)

    suspend fun getLocalUsers(): List<UserInfo> = dao.getAll()

    suspend fun deleteLocalUser(user: UserInfo) = dao.deleteAll(user)
}