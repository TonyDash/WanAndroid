package com.cjy.webviewlibrary.webProcess.callback

import android.webkit.WebResourceRequest
import android.webkit.WebView

interface WebViewCallBack {
    fun pageStarted(url: String)
    fun pageFinished(view: WebView, url: String)
    fun onError()
    fun updateTitle(title: String)
    fun shouldOverrideUrlLoading(request: WebResourceRequest):Boolean
}