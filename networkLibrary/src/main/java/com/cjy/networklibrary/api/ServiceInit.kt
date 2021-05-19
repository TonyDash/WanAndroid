package com.cjy.networklibrary.api

import com.cjy.networklibrary.client.RetrofitClient

object HomeApiService: HomeApi by RetrofitClient.instance.getRetrofit().create(HomeApi::class.java)
object UserApiService: UserApi by RetrofitClient.instance.getRetrofit().create(UserApi::class.java)
object CollectApiService: CollectApi by RetrofitClient.instance.getRetrofit().create(CollectApi::class.java)