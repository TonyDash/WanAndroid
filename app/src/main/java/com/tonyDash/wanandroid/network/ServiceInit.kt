package com.tonyDash.wanandroid.network

import com.cjy.networklibrary.client.RetrofitClient
import com.tonyDash.wanandroid.ui.main.home.api.HomeApi

//object UserApiService:UserApi by RetrofitClient.instance.getRetrofit().create(UserApi::class.java)
object HomeApiService: HomeApi by RetrofitClient.instance.getRetrofit().create(HomeApi::class.java)
