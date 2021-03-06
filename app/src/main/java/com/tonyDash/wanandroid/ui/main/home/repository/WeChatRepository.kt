package com.tonyDash.wanandroid.ui.main.home.repository

import com.cjy.networklibrary.api.HomeApi

class WeChatRepository(private val api: HomeApi) {
    suspend fun getWeChatCategories() = api.getWechatCategories().apiData()
    suspend fun getWeChatArticleList(page: Int, id: Int) =
        api.getWechatArticleList(page, id).apiData()
}