package com.tonyDash.wanandroid.ui.main.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.networklibrary.entity.Article
import com.tonyDash.wanandroid.ui.main.home.repository.LatestRepository

class LatestViewModel(private val repository: LatestRepository):BaseViewModel() {

    companion object {
        const val INITIAL_PAGE = 0
    }

    val articleList: MutableLiveData<MutableList<Article>> = MutableLiveData()

    var page = INITIAL_PAGE

    fun getListData(){
        (page== INITIAL_PAGE).yes {
            refreshProjectList()
        }.otherwise {
            loadMoreProjectList()
        }
    }

    private fun refreshProjectList() = launch {
        page = INITIAL_PAGE
        val pagination = repository.getProjectList(page)
        page = pagination.curPage
        articleList.value = pagination.datas.toMutableList()
    }

    private fun loadMoreProjectList() = launch {
        val pagination = repository.getProjectList(page)
        page = pagination.curPage
        val currentList = articleList.value ?: mutableListOf()
        currentList.addAll(pagination.datas)
        articleList.value = currentList
    }
}