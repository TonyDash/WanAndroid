package com.tonyDash.wanandroid

import android.app.Application

class App:Application() {

    override fun onCreate() {
        super.onCreate()
//        mApplication = this

        //启动器进行异步初始化
//        TaskDispatcher.init(this)
//        TaskDispatcher.createInstance()
//            .addTask(InitBuGlyTask())
//            .addTask(InitKoInTask())
//            .addTask(InitLiveEventBusTask())
//            .addTask(InitSmartRefreshLayoutTask())
//            .start()
    }
}