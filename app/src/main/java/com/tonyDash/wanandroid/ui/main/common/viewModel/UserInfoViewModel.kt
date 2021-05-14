package com.tonyDash.wanandroid.ui.main.common.viewModel

import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.ui.main.common.repository.UserInfoRepository

class UserInfoViewModel(private val repository: UserInfoRepository):BaseViewModel() {

    fun isLogin(): Boolean = emitNormal {
        repository.isLogin()
    }?:false
}