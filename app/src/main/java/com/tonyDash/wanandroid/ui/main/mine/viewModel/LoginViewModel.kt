package com.tonyDash.wanandroid.ui.main.mine.viewModel

import androidx.lifecycle.MutableLiveData
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.store.UserInfoStore
import com.tonyDash.wanandroid.ui.main.mine.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : BaseViewModel() {


    val loginResult = MutableLiveData<Boolean>()

    fun login(account: String, password: String) = launch {
        val userInfo = repository.login(account, password)
        UserInfoStore.setUserInfo(userInfo)
        loginResult.value = true
    }
}