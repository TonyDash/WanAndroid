package com.cjy.webviewlibrary.activity

import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.cjy.baselibrary.activity.BaseActivity
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.commonlibrary.autoservice.IWebViewService.Companion.PARAM_ARTICLE
import com.cjy.commonlibrary.store.UserInfoStore
import com.cjy.commonlibrary.utils.Constants.Companion.DATA_ARTICLE_ID
import com.cjy.commonlibrary.utils.Constants.Companion.COMMAND_NAME_CHANGE_COLLECT
import com.cjy.commonlibrary.utils.Constants.Companion.COMMAND_NAME_LOGIN
import com.cjy.commonlibrary.utils.Constants.Companion.DATA_IS_COLLECT
import com.cjy.networklibrary.entity.Article
import com.cjy.webviewlibrary.fragment.ActionFragment
import com.cjy.webviewlibrary.R
import com.cjy.webviewlibrary.ext.htmlToSpanned
import com.cjy.webviewlibrary.model.JsParam
import com.cjy.webviewlibrary.utils.whiteHostList
import com.cjy.webviewlibrary.viewModel.CollectViewModel
import com.cjy.webviewlibrary.viewModel.UserInfoViewModel
import com.cjy.webviewlibrary.webProcess.CommandDispatcher
import com.cjy.webviewlibrary.webProcess.callback.WebViewCallBack
import com.cjy.webviewlibrary.webProcess.webchromeclient.MyWebChromeClient
import com.cjy.webviewlibrary.webProcess.webviewclient.MyWebViewClient
import com.google.gson.JsonObject
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import kotlinx.android.synthetic.main.activity_agent_webview.*


class AgentWebViewActivity : BaseActivity(), WebViewCallBack {

    private lateinit var userViewModel:UserInfoViewModel
    private lateinit var collectViewModel:CollectViewModel

    private lateinit var article: Article

    private var agentWeb: AgentWeb? = null

    override fun getLayoutId(): Int = R.layout.activity_agent_webview

    override fun initViews() {
        userViewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        collectViewModel = ViewModelProvider(this).get(CollectViewModel::class.java)
        ivBack.setOnClickListener {
            this@AgentWebViewActivity.finish()
        }
        ivMore.setOnClickListener {
            ActionFragment.newInstance(article).show(supportFragmentManager)
        }
        collectViewModel.isCollect.observe(this,{
            article.collect = it
            val jsonParams = JsonObject()
            jsonParams.addProperty(DATA_ARTICLE_ID,article.id)
            jsonParams.addProperty(DATA_IS_COLLECT,it)
            takeNativeAction(COMMAND_NAME_CHANGE_COLLECT,jsonParams.toString())
        })
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
        CommandDispatcher.instance.initAidlConnection()
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

    fun changeCollect() {
        val isLogin = UserInfoStore.instance.isLogin()
            isLogin.yes {
            article.collect.yes {
                collectViewModel.unCollect(article.id)
            }.otherwise {
                collectViewModel.collect(article.id)
            }
        }.otherwise {
            takeNativeAction(COMMAND_NAME_LOGIN,"")
        }
    }

    /**
     * 执行APP内自身的进程间交互通信
     */
    private fun takeNativeAction(commandName:String,jsonParams:String) {
        CommandDispatcher.instance.executeCommand(
            commandName,
            jsonParams,
            this@AgentWebViewActivity
        )
    }

    /**
     * 执行与JS间的进程间交互通信
     */
    private fun takeJsAction(jsParam: String) {
        (jsParam.isNotEmpty()).yes {
            val jsParamObject = GsonUtil.instance.parse(jsParam, JsParam::class.java)
            jsParamObject?.run {
                CommandDispatcher.instance.executeCommand(
                    jsParamObject.name,
                    GsonUtil.instance.toJsonString(jsParamObject.params),
                    this@AgentWebViewActivity
                )
            }
        }
    }

    fun handleCallback(callName: String,status:Boolean, response: String) {
        when(callName){
            "login"->{
                status.yes {
                    changeCollect()
                }.otherwise {
                    Toast.makeText(this,"未登录",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}