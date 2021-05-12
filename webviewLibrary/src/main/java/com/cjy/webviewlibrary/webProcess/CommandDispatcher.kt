package com.cjy.webviewlibrary.webProcess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.cjy.baselibrary.AppContext
import com.cjy.webviewlibrary.I2MainProcessAidlInterface
import com.cjy.webviewlibrary.I2WebViewProcessAidlInterface
import com.cjy.webviewlibrary.activity.AgentWebViewActivity
import com.cjy.webviewlibrary.mainProcess.MainProcessCommandService

class CommandDispatcher private constructor() : ServiceConnection {

    private var i2MainProcessAidlInterface:I2MainProcessAidlInterface? = null

    companion object{
        val instance = Holder.holder
    }

    fun initAidlConnection(){
        AppContext.let {
            it.bindService(
                Intent().apply { this.setClass(it, MainProcessCommandService::class.java) },
                this,Context.BIND_AUTO_CREATE
            )
        }
    }


    private object Holder{
        val holder = CommandDispatcher()
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d("TAG","==========onServiceConnected=========")
        i2MainProcessAidlInterface = I2MainProcessAidlInterface.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        i2MainProcessAidlInterface = null
        initAidlConnection()
    }

    override fun onBindingDied(name: ComponentName?) {
        super.onBindingDied(name)
        i2MainProcessAidlInterface = null
        initAidlConnection()
    }


    fun executeCommand(commandName: String, jsonParams: String,activity:AgentWebViewActivity){
        i2MainProcessAidlInterface?.run {
            this.handleWebCommand(commandName, jsonParams, object : I2WebViewProcessAidlInterface.Stub(){
                override fun onResult(callName: String, response: String) {
                    activity.handleCallback(callName,response)
                }

            })
        }
    }
}