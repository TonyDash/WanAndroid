package com.tonyDash.wanandroid.ui.main.home.repository

import com.tonyDash.wanandroid.network.api.HomeApi

class SquareRepository(private val api: HomeApi) {
    suspend fun getUserArticleList(page: Int) =
        api.getUserArticleList(page).apiData()
}