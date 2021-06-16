package com.cjy.networklibrary.client

import android.util.Log
import com.cjy.baselibrary.AppContext
import com.cjy.networklibrary.BuildConfig
import com.cjy.networklibrary.constant.NetWorkConstant.Companion.BASE_URL
import com.cjy.networklibrary.constant.NetWorkConstant.Companion.TAG
import com.cjy.networklibrary.constant.NetWorkConstant.Companion.TIME_OUT
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor(){

    private val cookieJar = PersistentCookieJar(
        SetCookieCache(),
        SharedPrefsCookiePersistor(AppContext)
    )

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e(TAG, message)
            }
        }).also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

    private var okHttpClient: OkHttpClient = OkHttpClient.Builder()
//       .addInterceptor(AuthorizationInterceptor())
        .cookieJar(cookieJar)
        .callTimeout(TIME_OUT,TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
       .readTimeout(TIME_OUT, TimeUnit.SECONDS).also {
           if (BuildConfig.DEBUG) {
               it.addInterceptor(httpLoggingInterceptor)
           }
       }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    companion object {
        val instance: RetrofitClient = Holder.holder
    }

    fun getRetrofit() :Retrofit{
        return retrofit
    }

    fun clearCookie() = cookieJar.clear()

    fun getCookie(){
        val toString = okHttpClient.cookieJar.toString()
    }

    private object Holder {
        val holder = RetrofitClient()
    }
}