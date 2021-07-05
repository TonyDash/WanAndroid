package com.tonyDash.wanandroid.ui.flutter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MyFlutterFragment : FlutterFragment {

    var params: String = ""
    var routePage:String = ""

    companion object {
        const val KEY_INIT_PARAMS = "key_init_params"
    }

    constructor(context: Context, parmas: String = "", routePage: String):super(){
        this.params = params
        this.routePage = routePage
        //创建flutter发动机
        val flutterEngine = FlutterEngine(context)
        //指定想要跳转的flutter页面 这里要和下图对应上 记住他
        flutterEngine.navigationChannel.setInitialRoute(routePage)
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        //这里做一个缓存 可以在适当的地方执行他 比如MyApp里 或者未跳转flutterr之前 在flutter页面执行前预加载
        val flutterEngineCache = FlutterEngineCache.getInstance()
        //缓存可以缓存好多个 以不同的的id区分
        flutterEngineCache.put(routePage, flutterEngine)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getInitialRoute(): String? {
        return super.getInitialRoute()
    }

    fun getFlutterFragment():Fragment{
//        return withCachedEngine(routePage).build()
        return withNewEngine().initialRoute(routePage).build()
    }
}