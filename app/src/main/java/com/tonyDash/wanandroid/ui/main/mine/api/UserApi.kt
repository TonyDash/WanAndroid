package com.tonyDash.wanandroid.ui.main.mine.api

import com.cjy.networklibrary.result.ApiResult
import com.tonyDash.wanandroid.ui.main.mine.model.UserInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiResult<UserInfo>
}