package com.tonyDash.wanandroid.ui.main.home.repository

import com.tonyDash.wanandroid.ui.main.home.api.HomeApi

class ProjectRepository(private val api: HomeApi) {
    suspend fun getProjectCategories() = api.getProjectCategories().apiData()
    suspend fun getProjectListByCid(page: Int, cid: Int) =
        api.getProjectListByCid(page, cid).apiData()
}