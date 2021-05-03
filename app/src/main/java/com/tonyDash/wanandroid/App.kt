package com.tonyDash.wanandroid

import com.cjy.baselibrary.BaseApp
import com.tonyDash.wanandroid.task.InitKoInTask
import com.tonydash.launchstarter.TaskDispatcher

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        //启动器进行异步初始化
        TaskDispatcher.init(this)
        TaskDispatcher.createInstance()
//            .addTask(InitBuGlyTask())
            .addTask(InitKoInTask())
//            .addTask(InitLiveEventBusTask())
//            .addTask(InitSmartRefreshLayoutTask())
            .start()
    }
}