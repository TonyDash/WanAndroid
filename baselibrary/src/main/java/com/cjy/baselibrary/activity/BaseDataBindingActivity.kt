package com.cjy.baselibrary.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

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