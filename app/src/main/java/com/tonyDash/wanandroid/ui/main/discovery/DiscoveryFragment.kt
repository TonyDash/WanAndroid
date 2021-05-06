package com.tonyDash.wanandroid.ui.main.discovery

import com.cjy.baselibrary.fragment.BaseDataBindVMFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.FragmentHomeBinding

class DiscoveryFragment:BaseDataBindVMFragment<FragmentHomeBinding>(){

    companion object {
        fun newInstance() = DiscoveryFragment()
    }
    override fun getLayoutRes(): Int = R.layout.fragment_discovery

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {

    }

    override fun initData() {
    }
}