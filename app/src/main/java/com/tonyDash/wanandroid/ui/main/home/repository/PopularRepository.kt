package com.tonyDash.wanandroid.ui.main.home.repository

import com.tonyDash.wanandroid.ui.main.home.api.HomeApi

class PopularRepository(private val api:HomeApi) {
    suspend fun getTopArticleList() = api.getTopArticleList().apiData()
    suspend fun getArticleList(page: Int) = api.getArticleList(page).apiData()
}