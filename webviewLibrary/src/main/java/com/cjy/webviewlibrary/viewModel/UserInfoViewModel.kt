package com.cjy.webviewlibrary.viewModel

import androidx.lifecycle.LiveData
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.webviewlibrary.repository.UserInfoRepository

class UserInfoViewModel():BaseViewModel() {

    private val repository: UserInfoRepository by lazy { UserInfoRepository() }
}