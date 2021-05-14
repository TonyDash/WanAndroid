package com.tonyDash.wanandroid.ui.main.common.repository

import com.tonyDash.wanandroid.network.api.CollectApi

class CollectRepository(private val api: CollectApi) {

    suspend fun collect(id:Int) = api.collect(id).apiData()

    suspend fun unCollect(id:Int) = api.uncollect(id).apiData()
}