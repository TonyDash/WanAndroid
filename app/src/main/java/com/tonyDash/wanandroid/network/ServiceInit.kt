package com.tonyDash.wanandroid.network

import com.cjy.networklibrary.client.RetrofitClient
import com.tonyDash.wanandroid.network.api.HomeApi
import com.tonyDash.wanandroid.network.api.UserApi

object HomeApiService: HomeApi by RetrofitClient.instance.getRetrofit().create(HomeApi::class.java)
object UserApiService: UserApi by RetrofitClient.instance.getRetrofit().create(UserApi::class.java)
