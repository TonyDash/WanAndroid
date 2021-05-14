package com.tonyDash.wanandroid.ui.main.home.repository

import com.tonyDash.wanandroid.network.api.HomeApi

class LatestRepository(private val api: HomeApi) {
    suspend fun getProjectList(pageIndex: Int) = api.getProjectList(pageIndex).apiData()
}