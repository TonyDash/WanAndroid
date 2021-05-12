package com.cjy.webviewlibrary.mainProcess

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MainProcessCommandService : Service() {
    override fun onBind(intent: Intent?): IBinder {
        return MainProcessCommandManager.Instance
    }
}