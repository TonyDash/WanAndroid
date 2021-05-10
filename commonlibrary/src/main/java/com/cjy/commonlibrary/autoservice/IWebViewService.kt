package com.cjy.commonlibrary.autoservice

import android.app.Activity

interface IWebViewService {

    companion object {
        const val PARAM_ARTICLE = "param_article"
    }

    fun startWebViewActivity(fromActivity: Activity, params: Map<String, Any> = emptyMap())
}