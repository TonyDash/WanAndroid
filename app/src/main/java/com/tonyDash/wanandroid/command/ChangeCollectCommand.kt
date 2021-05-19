package com.tonyDash.wanandroid.command

import com.cjy.baselibrary.baseExt.no
import com.cjy.commonlibrary.I2WebViewProcessAidlInterface
import com.cjy.commonlibrary.command.ICommand
import com.cjy.commonlibrary.eventBus.LiveEventBus
import com.cjy.commonlibrary.utils.Constants
import com.google.auto.service.AutoService

@AutoService(ICommand::class)
class ChangeCollectCommand :ICommand{
    override fun name(): String {
        return Constants.COMMAND_NAME_CHANGE_COLLECT
    }

    override fun execute(params: Map<*, *>?, callback: I2WebViewProcessAidlInterface) {
        params?.run{
            val articleId:Double = this[Constants.DATA_ARTICLE_ID] as Double
            val isCollect:Boolean = this[Constants.DATA_IS_COLLECT] as Boolean
            LiveEventBus.post(Constants.LIVE_EVENT_CHANGE_COLLECT,articleId to isCollect)
        }
    }
}