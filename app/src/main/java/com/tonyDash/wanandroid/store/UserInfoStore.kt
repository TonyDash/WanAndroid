package com.tonyDash.wanandroid.store

import com.cjy.baselibrary.AppContext
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.networklibrary.entity.UserInfo
import com.tonyDash.wanandroid.ui.main.mine.utils.clearSpValue
import com.tonyDash.wanandroid.ui.main.mine.utils.getSpValue
import com.tonyDash.wanandroid.ui.main.mine.utils.putSpValue

object UserInfoStore {

    private const val SP_USER_INFO = "sp_user_info"
    private const val KEY_USER_INFO = "userInfo"

    fun isLogin(): Boolean {
        val userInfoStr = getSpValue(SP_USER_INFO, AppContext, KEY_USER_INFO, "")
        return userInfoStr.isNotEmpty()
    }

    fun getUserInfo(): UserInfo? {
        val userInfoStr = getSpValue(SP_USER_INFO, AppContext, KEY_USER_INFO, "")
        return if (userInfoStr.isNotEmpty()) {
            GsonUtil.instance.parse(userInfoStr, UserInfo::class.java)
        } else {
            null
        }
    }

    fun setUserInfo(userInfo: UserInfo) =
        putSpValue(
            SP_USER_INFO, AppContext, KEY_USER_INFO,
            GsonUtil.instance.toJsonString(userInfo)
        )

    fun clearUserInfo() {
        clearSpValue(SP_USER_INFO, AppContext)
    }
}