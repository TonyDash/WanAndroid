package com.tonyDash.wanandroid.ui.main.home.repository

import com.cjy.networklibrary.api.HomeApi

class SquareRepository(private val api: HomeApi) {
    suspend fun getUserArticleList(page: Int) =
        api.getUserArticleList(page).apiData()
}