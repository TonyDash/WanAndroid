package com.tonyDash.wanandroid.ui.main.home.fragment

import com.cjy.baselibrary.fragment.BaseDataBindVMFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.FragmentLatestBinding

class LatestFragment:BaseDataBindVMFragment<FragmentLatestBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_latest

    override fun getViewModel(): BaseViewModel = BaseViewModel()

    companion object {
        fun newInstance() = LatestFragment()
    }
    override fun initView() {

    }
}