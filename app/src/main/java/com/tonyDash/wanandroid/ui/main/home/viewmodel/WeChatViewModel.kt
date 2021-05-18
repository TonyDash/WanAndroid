package com.tonyDash.wanandroid.ui.main.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.networklibrary.entity.Article
import com.cjy.networklibrary.entity.Category
import com.tonyDash.wanandroid.ui.main.home.repository.ProjectRepository
import com.tonyDash.wanandroid.ui.main.home.repository.WeChatRepository

class WeChatViewModel(private val repository: WeChatRepository) : BaseViewModel() {

    val categories: MutableLiveData<MutableList<Category>> = MutableLiveData()
    val checkedPosition: MutableLiveData<Int> = MutableLiveData()
    val articleList: MutableLiveData<MutableList<Article>> = MutableLiveData()

    companion object {
        const val INITIAL_CHECKED = 0
        const val INITIAL_PAGE = 1
    }

    var page = INITIAL_PAGE + 1

    fun getWeChatCategory() = launch {
        val categoryList = repository.getWeChatCategories()
        val checkedPosition = INITIAL_CHECKED
        val cid = categoryList[checkedPosition].id
        categoryList[checkedPosition].isChecked = true
        val pagination = repository.getWeChatArticleList(INITIAL_PAGE, cid)
        page = pagination.curPage
        categories.value = categoryList
        this@WeChatViewModel.checkedPosition.value = checkedPosition
        articleList.value = pagination.datas.toMutableList()
    }

    fun refreshWeChatList(
        checkedPosition: Int = this.checkedPosition.value ?: INITIAL_CHECKED
    ) =
        launch {
            val categoryList = categories.value ?: return@launch
            if (checkedPosition != this@WeChatViewModel.checkedPosition.value) {
                categoryList[this@WeChatViewModel.checkedPosition.value
                    ?: INITIAL_CHECKED].isChecked = false
                articleList.value = mutableListOf()
                this@WeChatViewModel.checkedPosition.value = checkedPosition
            }
            val cid = categoryList[checkedPosition].id
            categoryList[checkedPosition].isChecked = true
            val pagination = repository.getWeChatArticleList(INITIAL_PAGE, cid)
            page = pagination.curPage
            articleList.value = pagination.datas.toMutableList()
        }

    fun loadMoreWeChatList() = launch {
        val categoryList = categories.value ?: return@launch
        val checkedPosition = checkedPosition.value ?: return@launch
        val cid = categoryList[checkedPosition].id
        val pagination = repository.getWeChatArticleList(page + 1, cid)
        page = pagination.curPage

        val currentList = articleList.value ?: mutableListOf()
        currentList.addAll(pagination.datas)

        articleList.value = currentList
    }
}