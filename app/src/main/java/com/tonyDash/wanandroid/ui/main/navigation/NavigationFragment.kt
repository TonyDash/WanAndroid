package com.tonyDash.wanandroid.ui.main.navigation

import com.cjy.baselibrary.fragment.BaseDataBindVMFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.FragmentHomeBinding

class NavigationFragment:BaseDataBindVMFragment<FragmentHomeBinding>(){

    companion object {
        fun newInstance() = NavigationFragment()
    }
    override fun getLayoutRes(): Int = R.layout.fragment_navigation

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {

    }
}