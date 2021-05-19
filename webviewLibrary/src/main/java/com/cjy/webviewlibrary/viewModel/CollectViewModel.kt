package com.cjy.webviewlibrary.viewModel

import androidx.lifecycle.MutableLiveData
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.webviewlibrary.repository.CollectRepository

class CollectViewModel() : BaseViewModel() {
    private val repository: CollectRepository by lazy { CollectRepository() }

    val isCollect:MutableLiveData<Boolean> = MutableLiveData()

    fun collect(articleId: Int) = launch {
        repository.collect(articleId)
        isCollect.value = true
    }

    fun unCollect(articleId: Int) = launch {
        repository.unCollect(articleId)
        isCollect.value = false
    }
}