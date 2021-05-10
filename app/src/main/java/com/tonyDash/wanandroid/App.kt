package com.tonyDash.wanandroid

import com.cjy.baselibrary.BaseApp
import com.cjy.baselibrary.adapter.ActivityLifecycleCallbacksAdapter
import com.cjy.baselibrary.utils.ActivityManager
import com.cjy.baselibrary.utils.isMainProcess
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

        // 主进程初始化
        if (isMainProcess(this)) {
            init()
        }
    }

    private fun init() {
        registerActivityCallbacks()
//        setDayNightMode()
    }

//    private fun setDayNightMode() {
//        setNightMode(SettingsStore.getNightMode())
//    }

    private fun registerActivityCallbacks() {
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksAdapter(
            onActivityCreated = { activity, _ ->
                ActivityManager.activities.add(activity)
            },
            onActivityDestroyed = { activity ->
                ActivityManager.activities.remove(activity)
            }
        ))
    }
}