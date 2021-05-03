package com.cjy.baselibrary

import android.app.Application
import android.content.ContextWrapper

private lateinit var mApplication: Application

open class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        mApplication = this
    }
}

object AppContext : ContextWrapper(mApplication)//ContextWrapper对Context上下文进行包装(装饰者模式)