package com.tonyDash.wanandroid.task

import com.tonydash.launchstarter.task.Task
import org.koin.core.context.startKoin

class InitKoInTask :Task() {
    override fun run() {
        startKoin {
//            modules(appModule)
        }
    }
}