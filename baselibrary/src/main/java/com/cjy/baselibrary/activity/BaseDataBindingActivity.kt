package com.cjy.baselibrary.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * 纯viewDataBinding，没有viewModel层
 * 当activity不需要viewModel层分离操作时，继承此类
 */
abstract class BaseDataBindingActivity<DB : ViewDataBinding> : BaseActivity() {

    lateinit var dataBinding: DB

    override fun setContentLayout() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        initViews()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding.unbind()
    }
}