package com.cjy.webviewlibrary.webProcess.webviewclient

import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.webviewlibrary.WebViewCallBack
import com.just.agentweb.WebViewClient

class MyWebViewClient : WebViewClient {

    private var webViewCallBack: WebViewCallBack? = null
    private val TAG = "WebViewClient"

    constructor(webViewCallBack: WebViewCallBack? = null) : super() {
        this.webViewCallBack = webViewCallBack
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest): Boolean {
        (webViewCallBack != null).yes {
            return webViewCallBack?.shouldOverrideUrlLoading(request)
                ?: super.shouldOverrideUrlLoading(view, request)
        }.otherwise {
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        webViewCallBack?.pageFinished(view,url)
    }
}