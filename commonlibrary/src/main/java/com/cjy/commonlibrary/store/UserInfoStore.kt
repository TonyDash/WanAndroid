package com.cjy.commonlibrary.store

import android.util.Log
import com.cjy.baselibrary.AppContext
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.commonlibrary.utils.Constants.Companion.KEY_IS_LOGIN
import com.cjy.commonlibrary.utils.Constants.Companion.KEY_USER_DATA
import com.cjy.commonlibrary.utils.Constants.Companion.SP_USER_INFO
import com.cjy.commonlibrary.utils.getSpValue
import com.cjy.commonlibrary.utils.putSpValue
import com.cjy.commonlibrary.utils.removeSpValue
import com.cjy.networklibrary.entity.UserInfo

class UserInfoStore {

    companion object {
        val instance = Holder.holder
    }

    fun isLogin(): Boolean = getSpValue(SP_USER_INFO, AppContext, KEY_IS_LOGIN, false)

    fun saveUserInfo(user: UserInfo) {
        putSpValue(SP_USER_INFO, AppContext, KEY_IS_LOGIN, true)
        putSpValue(SP_USER_INFO, AppContext, KEY_USER_DATA, GsonUtil.instance.toJsonString(user))
    }

    fun cleanUserInfo() {
        putSpValue(SP_USER_INFO, AppContext, KEY_IS_LOGIN, false)
        removeSpValue(SP_USER_INFO, AppContext, KEY_USER_DATA)
    }

    fun getUserInfo(): UserInfo {
        val userStr = getSpValue(SP_USER_INFO, AppContext, KEY_USER_DATA, "")
        return GsonUtil.instance.parse(userStr, UserInfo::class.java) ?: UserInfo()
    }

    private object Holder {
        val holder = UserInfoStore()
    }
}