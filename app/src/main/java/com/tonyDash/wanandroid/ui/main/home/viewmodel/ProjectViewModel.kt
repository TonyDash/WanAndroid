package com.tonyDash.wanandroid.ui.main.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.ui.main.home.model.Article
import com.tonyDash.wanandroid.ui.main.home.model.Category
import com.tonyDash.wanandroid.ui.main.home.repository.ProjectRepository

class ProjectViewModel(private val repository: ProjectRepository) : BaseViewModel() {

    val categories: MutableLiveData<MutableList<Category>> = MutableLiveData()
    val checkedPosition: MutableLiveData<Int> = MutableLiveData()
    val articleList: MutableLiveData<MutableList<Article>> = MutableLiveData()

    companion object {
        const val INITIAL_CHECKED = 0
        const val INITIAL_PAGE = 1
    }

    var page = INITIAL_PAGE + 1

    fun getProjectCategory() = launch {
        val categoryList = repository.getProjectCategories()
        val checkedPosition = INITIAL_CHECKED
        val cid = categoryList[checkedPosition].id
        categoryList[checkedPosition].isChecked = true
        val pagination = repository.getProjectListByCid(INITIAL_PAGE, cid)
        page = pagination.curPage
        categories.value = categoryList
        this@ProjectViewModel.checkedPosition.value = checkedPosition
        articleList.value = pagination.datas.toMutableList()
    }

    fun refreshProjectList(
        checkedPosition: Int = this.checkedPosition.value ?: INITIAL_CHECKED
    ) =
        launch {
            val categoryList = categories.value ?: return@launch
            if (checkedPosition != this@ProjectViewModel.checkedPosition.value) {
                categoryList[this@ProjectViewModel.checkedPosition.value
                    ?: INITIAL_CHECKED].isChecked = false
                articleList.value = mutableListOf()
                this@ProjectViewModel.checkedPosition.value = checkedPosition
            }
            val cid = categoryList[checkedPosition].id
            categoryList[checkedPosition].isChecked = true
            val pagination = repository.getProjectListByCid(INITIAL_PAGE, cid)
            page = pagination.curPage
            articleList.value = pagination.datas.toMutableList()
        }

    fun loadMoreProjectList() = launch {
        val categoryList = categories.value ?: return@launch
        val checkedPosition = checkedPosition.value ?: return@launch
        val cid = categoryList[checkedPosition].id
        val pagination = repository.getProjectListByCid(page + 1, cid)
        page = pagination.curPage

        val currentList = articleList.value ?: mutableListOf()
        currentList.addAll(pagination.datas)

        articleList.value = currentList
    }
}