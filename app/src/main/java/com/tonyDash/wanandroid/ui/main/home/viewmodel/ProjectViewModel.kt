package com.tonyDash.wanandroid.ui.main.home.viewmodel

import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.ui.main.home.repository.ProjectRepository

class ProjectViewModel(private val repository: ProjectRepository):BaseViewModel() {

    companion object {
        const val INITIAL_CHECKED = 0
        const val INITIAL_PAGE = 1
    }
}