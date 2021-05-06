package com.tonyDash.wanandroid.ui.main.mine

import com.cjy.baselibrary.fragment.BaseDataBindVMFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.FragmentHomeBinding

class MineFragment:BaseDataBindVMFragment<FragmentHomeBinding>(){

    companion object {
        fun newInstance() = MineFragment()
    }
    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {

    }

    override fun initData() {

    }
}