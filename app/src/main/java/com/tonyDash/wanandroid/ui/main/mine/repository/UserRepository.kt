package com.tonyDash.wanandroid.ui.main.mine.repository

import com.tonyDash.wanandroid.ui.main.mine.api.UserApi

class UserRepository(private val api:UserApi) {
    suspend fun login(username: String, password: String) =
        api.login(username, password).apiData()
}