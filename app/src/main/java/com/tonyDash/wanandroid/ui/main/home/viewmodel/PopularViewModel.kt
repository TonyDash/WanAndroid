package com.tonyDash.wanandroid.ui.main.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.networklibrary.result.ApiResult
import com.cjy.networklibrary.result.Pagination
import com.cjy.networklibrary.entity.Article
import com.tonyDash.wanandroid.ui.main.home.repository.PopularRepository

class PopularViewModel(private val repository: PopularRepository) : BaseViewModel() {

    companion object {
        const val INITIAL_PAGE = 0
    }

    val articleList: MutableLiveData<MutableList<Article>> = MutableLiveData()

    var page = INITIAL_PAGE

    fun getListData(){
        (page== INITIAL_PAGE).yes {
            refreshArticleList()
        }.otherwise {
            loadMoreArticleList()
        }
    }

    private fun refreshArticleList() = launch {
        val topListDeferred = async {
            repository.getTopArticleList()
        }
        page = INITIAL_PAGE
        val paginationDeferred = async {
            repository.getArticleList(page)
        }
        val topArticleList = topListDeferred.await().onEach {
            it.top = true
        }
        val pagination = paginationDeferred.await()
        page = pagination.curPage
        articleList.value = mutableListOf<Article>().apply {
            addAll(topArticleList)
            addAll(pagination.datas)
        }
    }

    private fun loadMoreArticleList() = launch {
        val pagination = repository.getArticleList(page)
        page = pagination.curPage
        val currentList = articleList.value ?: mutableListOf()
        currentList.addAll(pagination.datas)
        articleList.value = currentList
    }
}