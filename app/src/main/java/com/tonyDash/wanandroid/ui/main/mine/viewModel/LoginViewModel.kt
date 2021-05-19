package com.tonyDash.wanandroid.ui.main.mine.viewModel

import androidx.lifecycle.MutableLiveData
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.ui.main.common.repository.UserInfoRepository
import com.cjy.commonlibrary.store.UserInfoStore
import com.cjy.networklibrary.entity.UserInfo
import com.tonyDash.wanandroid.ui.main.mine.repository.LoginRepository

class LoginViewModel(
    private val repository: LoginRepository,
    private val userInfoRepository: UserInfoRepository
) : BaseViewModel() {

    val loginResult = MutableLiveData<Boolean>()

    fun login(account: String, password: String) = launch {
        val userInfo = repository.login(account, password)
        UserInfoStore.instance.saveUserInfo(userInfo)
        val localUsers = userInfoRepository.getLocalUsers()
        val existUser: UserInfo? = localUsers.find { it.id == userInfo.id }
        if (existUser == null) {
            userInfoRepository.saveLocalUser(userInfo)
        } else {
            userInfoRepository.updateLocalUser(userInfo)
        }
        loginResult.value = true
    }

    fun logout() {
        UserInfoStore.instance.cleanUserInfo()
        loginResult.value = false
    }
}