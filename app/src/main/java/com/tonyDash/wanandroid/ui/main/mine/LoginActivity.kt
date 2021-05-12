package com.tonyDash.wanandroid.ui.main.mine

import com.cjy.baselibrary.activity.BaseDataBindVMActivity
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.ActivityLoginBinding

class LoginActivity:BaseDataBindVMActivity<ActivityLoginBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {
    }

    override fun getViewModel(): BaseViewModel = BaseViewModel()
}