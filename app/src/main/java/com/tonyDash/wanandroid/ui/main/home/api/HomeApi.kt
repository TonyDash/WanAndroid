package com.tonyDash.wanandroid.ui.main.home.api

import com.cjy.networklibrary.result.ApiResult
import com.cjy.networklibrary.result.Pagination
import com.tonyDash.wanandroid.ui.main.home.model.Article
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {

    @GET("/article/top/json")
    suspend fun getTopArticleList(): ApiResult<List<Article>>

    @GET("/article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): ApiResult<Pagination<Article>>

}