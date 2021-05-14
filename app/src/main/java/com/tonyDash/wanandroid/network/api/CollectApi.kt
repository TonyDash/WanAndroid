package com.tonyDash.wanandroid.network.api

import com.cjy.networklibrary.result.ApiResult
import retrofit2.http.POST
import retrofit2.http.Path

interface CollectApi {

    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): ApiResult<Any?>

    @POST("lg/uncollect_originId/{id}/json")
    suspend fun uncollect(@Path("id") id: Int): ApiResult<Any?>
}