package com.tonyDash.wanandroid.ui.main.mine.repository

import com.tonyDash.wanandroid.network.api.UserApi

class LoginRepository(private val api: UserApi) {
    suspend fun login(username: String, password: String) =
        api.login(username, password).apiData()
}