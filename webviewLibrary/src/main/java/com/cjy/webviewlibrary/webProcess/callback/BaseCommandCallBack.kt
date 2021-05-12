package com.cjy.webviewlibrary.webProcess.callback

import com.cjy.webviewlibrary.model.CallBackResult

interface BaseCommandCallBack {
    fun onFinish(result: CallBackResult)
}