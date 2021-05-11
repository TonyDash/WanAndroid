package com.cjy.webviewlibrary.activity

import android.net.Uri
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.cjy.baselibrary.activity.BaseActivity
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.utils.ActivityManager
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.commonlibrary.autoservice.IWebViewService
import com.cjy.webviewlibrary.R
import com.cjy.webviewlibrary.ext.htmlToSpanned
import com.cjy.webviewlibrary.model.Article
import com.cjy.webviewlibrary.utils.whiteHostList
import com.cjy.webviewlibrary.webProcess.callback.WebViewCallBack
import com.cjy.webviewlibrary.webProcess.setting.WebViewDefaultSettings
import com.cjy.webviewlibrary.webProcess.webchromeclient.MyWebChromeClient
import com.cjy.webviewlibrary.webProcess.webviewclient.MyWebViewClient
import kotlinx.android.synthetic.main.activity_default_webview.*
import kotlinx.android.synthetic.main.activity_default_webview.ivBack
import kotlinx.android.synthetic.main.activity_default_webview.ivMore
import kotlinx.android.synthetic.main.activity_default_webview.tvTitle

class DefaultWebViewActivity:BaseActivity(), WebViewCallBack {


    private lateinit var article: Article

    override fun getLayoutId() = R.layout.activity_default_webview

    override fun initViews() {
        ivBack.setOnClickListener {
            ActivityManager.finish(DefaultWebViewActivity::class.java)
        }
        ivMore.setOnClickListener {

        }
//        WebViewDefaultSettings.instance.setSettings(WvDefault)
        WvDefault.webChromeClient = MyWebChromeClient(this)
        WvDefault.webViewClient = MyWebViewClient(this)
    }


    override fun initData() {
        val articleStr: String = intent?.getStringExtra(IWebViewService.PARAM_ARTICLE) ?: return
        (articleStr.isNotEmpty()).yes {
            article = GsonUtil.instance.parse(articleStr, Article::class.java) ?: Article()
        }
        tvTitle.text = article.title.htmlToSpanned()
        WvDefault.loadUrl(article.link)
    }


    /**
     * 加载js，去掉掘金、简书、CSDN等H5页面的Title、底部操作栏，以及部分广告
     */
    private fun customJs(url: String? = article.link): String {
        val js = StringBuilder()
        js.append("javascript:(function(){")
        when (Uri.parse(url).host) {
            "juejin.im" -> {
                js.append("var headerList = document.getElementsByClassName('main-header-box');")
                js.append("if(headerList&&headerList.length){headerList[0].parentNode.removeChild(headerList[0])}")
                js.append("var openAppList = document.getElementsByClassName('open-in-app');")
                js.append("if(openAppList&&openAppList.length){openAppList[0].parentNode.removeChild(openAppList[0])}")
                js.append("var actionBox = document.getElementsByClassName('action-box');")
                js.append("if(actionBox&&actionBox.length){actionBox[0].parentNode.removeChild(actionBox[0])}")
                js.append("var actionBarList = document.getElementsByClassName('action-bar');")
                js.append("if(actionBarList&&actionBarList.length){actionBarList[0].parentNode.removeChild(actionBarList[0])}")
                js.append("var columnViewList = document.getElementsByClassName('column-view');")
                js.append("if(columnViewList&&columnViewList.length){columnViewList[0].style.margin = '0px'}")
            }
            "www.jianshu.com" -> {
                js.append("var jianshuHeader = document.getElementById('jianshu-header');")
                js.append("if(jianshuHeader){jianshuHeader.parentNode.removeChild(jianshuHeader)}")
                js.append("var headerShimList = document.getElementsByClassName('header-shim');")
                js.append("if(headerShimList&&headerShimList.length){headerShimList[0].parentNode.removeChild(headerShimList[0])}")
                js.append("var fubiaoList = document.getElementsByClassName('fubiao-dialog');")
                js.append("if(fubiaoList&&fubiaoList.length){fubiaoList[0].parentNode.removeChild(fubiaoList[0])}")
                js.append("var ads = document.getElementsByClassName('note-comment-above-ad-wrap');")
                js.append("if(ads&&ads.length){ads[0].parentNode.removeChild(ads[0])}")

                js.append("var lazyShimList = document.getElementsByClassName('v-lazy-shim');")
                js.append("if(lazyShimList&&lazyShimList.length&&lazyShimList[0]){lazyShimList[0].parentNode.removeChild(lazyShimList[0])}")
                js.append("if(lazyShimList&&lazyShimList.length&&lazyShimList[1]){lazyShimList[1].parentNode.removeChild(lazyShimList[1])}")
            }
            "blog.csdn.net" -> {
                js.append("var csdnToolBar = document.getElementById('csdn-toolbar');")
                js.append("if(csdnToolBar){csdnToolBar.parentNode.removeChild(csdnToolBar)}")
                js.append("var csdnMain = document.getElementById('main');")
                js.append("if(csdnMain){csdnMain.style.margin='0px'}")
                js.append("var operate = document.getElementById('operate');")
                js.append("if(operate){operate.parentNode.removeChild(operate)}")
            }
        }
        js.append("})()")
        (js.toString()=="javascript:(function(){})()").yes {
            return url?:""
        }.otherwise {
        return js.toString()
        }
    }

    fun refreshPage() {
        WvDefault.reload()
    }

    override fun pageStarted(url: String) {
        Log.d("TAG", "pageStarted")
    }

    override fun pageFinished(view: WebView, url: String) {
        view.loadUrl(customJs(url))
    }

    override fun onError() {

    }

    override fun updateTitle(title: String) {
        setTitle(title)
    }

    override fun shouldOverrideUrlLoading(request: WebResourceRequest): Boolean {
        return !whiteHostList().contains(request.url?.host)
    }
}