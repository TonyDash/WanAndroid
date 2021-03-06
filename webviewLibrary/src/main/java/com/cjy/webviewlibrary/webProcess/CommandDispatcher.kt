package com.cjy.webviewlibrary.webProcess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.cjy.baselibrary.AppContext
import com.cjy.commonlibrary.I2MainProcessAidlInterface
import com.cjy.commonlibrary.I2WebViewProcessAidlInterface
import com.cjy.webviewlibrary.activity.AgentWebViewActivity
import com.cjy.webviewlibrary.mainProcess.MainProcessCommandService
import com.cjy.webviewlibrary.model.CallBackResult
import com.cjy.webviewlibrary.webProcess.callback.BaseCommandCallBack

class CommandDispatcher private constructor() : ServiceConnection {

    private var i2MainProcessAidlInterface: I2MainProcessAidlInterface? = null

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

    fun executeCommand(commandName: String, jsonParams: String,callBack:BaseCommandCallBack){
        i2MainProcessAidlInterface?.run {
            this.handleWebCommand(commandName, jsonParams, object : I2WebViewProcessAidlInterface.Stub(){
                override fun onResult(callName: String,status:Boolean, response: String) {
                    callBack.onFinish(CallBackResult(callName,status,response))
                }
            })
        }
    }

    fun executeCommand(commandName: String, jsonParams: String,activity:AgentWebViewActivity){
        i2MainProcessAidlInterface?.run {
            this.handleWebCommand(commandName, jsonParams, object : I2WebViewProcessAidlInterface.Stub(){
                override fun onResult(callName: String,status:Boolean, response: String) {
                    activity.handleCallback(callName,status,response)
                }

            })
        }
    }
}