package com.cjy.webviewlibrary.webProcess.webchromeclient

import android.webkit.WebView
import com.cjy.webviewlibrary.WebViewCallBack
import com.just.agentweb.WebChromeClient

class MyWebChromeClient:WebChromeClient {
    private var webViewCallBack: WebViewCallBack? = null
    private val TAG = "WebChromeClient"

    constructor(webViewCallBack: WebViewCallBack? = null):super(){
        this.webViewCallBack = webViewCallBack
    }

    override fun onReceivedTitle(view: WebView, title: String) {
        webViewCallBack?.run {
            updateTitle(title)
        }
        super.onReceivedTitle(view, title)
    }
}