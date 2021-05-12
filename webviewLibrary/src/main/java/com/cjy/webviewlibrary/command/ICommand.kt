package com.cjy.webviewlibrary.command

import com.cjy.webviewlibrary.I2WebViewProcessAidlInterface

interface ICommand {
    fun name():String
    fun execute(params:Map<*,*>?,callback: I2WebViewProcessAidlInterface)
}