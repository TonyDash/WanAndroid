package com.cjy.webviewlibrary

import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.cjy.baselibrary.activity.BaseActivity
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.utils.ActivityManager
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.commonlibrary.autoservice.IWebViewService.Companion.PARAM_ARTICLE
import com.cjy.webviewlibrary.ext.htmlToSpanned
import com.cjy.webviewlibrary.model.Article
import com.cjy.webviewlibrary.utils.whiteHostList
import com.cjy.webviewlibrary.webProcess.webchromeclient.MyWebChromeClient
import com.cjy.webviewlibrary.webProcess.webviewclient.MyWebViewClient
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*
import org.json.JSONObject

class WebViewActivity : BaseActivity(), WebViewCallBack {

    private lateinit var article: Article

    private var agentWeb: AgentWeb? = null

    override fun getLayoutId(): Int = R.layout.activity_webview


    override fun initViews() {
        ivBack.setOnClickListener {
            ActivityManager.finish(WebViewActivity::class.java)
        }
        ivMore.setOnClickListener {

        }
    }

    override fun initData() {
        val articleStr: String = intent?.getStringExtra(PARAM_ARTICLE) ?: return
        (articleStr.isNotEmpty()).yes {
            article = GsonUtil.instance.parse(articleStr, Article::class.java) ?: Article()
        }
        tvTitle.text = article.title.htmlToSpanned()
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(webContainer, ViewGroup.LayoutParams(-1, -1))
            .useDefaultIndicator(getColor(R.color.textColorPrimary), 2)
            .interceptUnkownUrl()
            .setMainFrameErrorView(R.layout.include_reload, R.id.btnReload)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
            .setWebChromeClient(MyWebChromeClient(this))
            .setWebViewClient(MyWebViewClient(this))
            .createAgentWeb()
            .ready()
            .get()

        agentWeb?.webCreator?.webView?.run {
            overScrollMode = WebView.OVER_SCROLL_NEVER
            settings.run {
                javaScriptCanOpenWindowsAutomatically = false
                loadsImagesAutomatically = true
                useWideViewPort = true
                loadWithOverviewMode = true
                textZoom = 100
            }
        }
        agentWeb?.urlLoader?.loadUrl(article.link)
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
        return js.toString()
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (agentWeb?.handleKeyEvent(keyCode, event) == true) {
            return true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    fun refreshPage() {
        agentWeb?.urlLoader?.reload()
    }

    override fun onPause() {
        agentWeb?.webLifeCycle?.onPause()
        super.onPause()

    }

    override fun onResume() {
        agentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        agentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }

    override fun pageStarted(url: String) {

    }

    override fun pageFinished(view: WebView,url: String) {
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