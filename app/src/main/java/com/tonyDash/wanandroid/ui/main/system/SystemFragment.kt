package com.tonyDash.wanandroid.ui.main.system

import com.cjy.baselibrary.fragment.BaseDataBindVMFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.FragmentHomeBinding

class SystemFragment:BaseDataBindVMFragment<FragmentHomeBinding>(){

    companion object {
        fun newInstance() = SystemFragment()
    }
    override fun getLayoutRes(): Int = R.layout.fragment_system

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {

    }

    override fun initData() {

    }
}