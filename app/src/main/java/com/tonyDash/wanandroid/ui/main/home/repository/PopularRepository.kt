package com.tonyDash.wanandroid.ui.main.home.repository

import com.cjy.networklibrary.api.HomeApi

class PopularRepository(private val api: HomeApi) {
    suspend fun getTopArticleList() = api.getTopArticleList().apiData()
    suspend fun getArticleList(page: Int) = api.getArticleList(page).apiData()
}