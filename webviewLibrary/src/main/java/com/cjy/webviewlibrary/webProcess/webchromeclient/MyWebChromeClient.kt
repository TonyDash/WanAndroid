package com.cjy.webviewlibrary.webProcess.webchromeclient

import android.util.Log
import android.webkit.GeolocationPermissions
import android.webkit.WebView
import com.cjy.webviewlibrary.webProcess.callback.WebViewCallBack
import com.just.agentweb.WebChromeClient

class MyWebChromeClient:WebChromeClient {
    private var webViewCallBack: WebViewCallBack? = null
    private val TAG = "WebChromeClient"
    var isGeolocationPermissionsRequest:Boolean=false;

    constructor(webViewCallBack: WebViewCallBack? = null):super(){
        this.webViewCallBack = webViewCallBack
    }

    override fun onReceivedTitle(view: WebView, title: String) {
        webViewCallBack?.run {
            updateTitle(title)
        }
        super.onReceivedTitle(view, title)
    }

    //解决问题位置。
    override fun onGeolocationPermissionsShowPrompt(origin: String?, callback: GeolocationPermissions.Callback?) {
        Log.i("ExDownload", "ExDownload------- onGeolocationPermissionsShowPrompt----- -${isGeolocationPermissionsRequest}")
        if(!isGeolocationPermissionsRequest) {
            isGeolocationPermissionsRequest=true
            super.onGeolocationPermissionsShowPrompt(origin, callback)
        }
    }
}