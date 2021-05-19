package com.cjy.commonlibrary.utils

class Constants {
    companion object{
        /**
         * autoService的组件化命令，用COMMAND开头
         */
        const val COMMAND_NAME_LOGIN = "login"
        const val COMMAND_NAME_CHANGE_COLLECT = "changeCollect"

        /**
         * 传递数据，用DATA开头
         */
        const val DATA_ARTICLE_ID = "articleId"
        const val DATA_IS_COLLECT = "isCollect"

        /**
         * LiveEventBus更新数据，用LIVE_EVENT开头
         */
        const val LIVE_EVENT_CHANGE_COLLECT = "liveEventChangeCollect"

        const val SP_USER_INFO = "spUserInfo"

        const val KEY_IS_LOGIN = "isLogin"
        const val KEY_USER_DATA = "userData"
    }
}