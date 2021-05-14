package com.tonyDash.wanandroid.room.viewModel

import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.room.repository.UserInfoRepository

class UserInfoViewModel(private val repository: UserInfoRepository):BaseViewModel() {

    fun isLogin(): Boolean = emitNormal {
        repository.isLogin()
    }?:false
}