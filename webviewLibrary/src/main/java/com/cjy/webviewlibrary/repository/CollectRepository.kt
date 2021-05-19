package com.cjy.webviewlibrary.repository

import com.cjy.networklibrary.api.CollectApi
import com.cjy.networklibrary.api.CollectApiService

class CollectRepository {

    private val api: CollectApi = CollectApiService

    suspend fun collect(id:Int) = api.collect(id).apiData()

    suspend fun unCollect(id:Int) = api.uncollect(id).apiData()
}