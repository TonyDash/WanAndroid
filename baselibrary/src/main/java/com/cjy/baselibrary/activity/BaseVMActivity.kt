package com.cjy.baselibrary.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.baselibrary.viewModel.ErrorState
import com.cjy.baselibrary.viewModel.LoadState
import com.cjy.baselibrary.viewModel.SuccessState

abstract class BaseVMActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
    }

    protected fun initViewModelAction() {
        getViewModel().let { baseViewModel ->
            baseViewModel.mStateLiveData.observe(this, Observer { stateActionState ->
                when (stateActionState) {
                    LoadState -> showLoading()
                    SuccessState -> dismissLoading()
                    is ErrorState -> {
                        dismissLoading()
                        stateActionState.message?.apply {
//                            errorToast(this)
                            handleError(this)
                        }
                    }
                }
            })
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): BaseViewModel

    abstract fun initView()

    open fun setContentLayout() {
        setContentView(getLayoutId())
        initViewModelAction()
        initView()
        initData()
    }

    open fun initData() {

    }

    open fun showLoading() {

    }

    open fun dismissLoading() {

    }

    open fun handleError(errorStr:String) {
        Toast.makeText(this,errorStr,Toast.LENGTH_SHORT).show()
    }
}