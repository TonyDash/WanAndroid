package com.cjy.commonlibrary.command

import com.cjy.commonlibrary.I2WebViewProcessAidlInterface

interface ICommand {
    fun name():String
    fun execute(params:Map<*,*>?,callback: I2WebViewProcessAidlInterface)
}