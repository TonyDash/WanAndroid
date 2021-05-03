package com.cjy.baselibrary.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
    }

    open fun setContentLayout() {
        setContentView(getLayoutId())
        initViews()
        initData()
    }

    abstract fun initData()

    abstract fun initViews()

    abstract fun getLayoutId(): Int
}