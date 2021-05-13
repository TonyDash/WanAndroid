package com.tonyDash.wanandroid.ui.main.mine.activity

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.cjy.baselibrary.activity.BaseVMActivity
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.utils.ActivityManager
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.ui.main.mine.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseVMActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel = viewModel

    override fun initView() {
        ivClose.setOnClickListener {
            ActivityManager.finish(LoginActivity::class.java)
        }
        btnLogin.setOnClickListener {
            checkAccountAndPassword()
        }
        tietPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                btnLogin.performClick()
                true
            } else {
                false
            }
        }
        tvGoRegister.setOnClickListener {

        }
    }

    override fun initData() {
        viewModel.loginResult.observe(this, Observer {
            it.yes {
                ActivityManager.finish(LoginActivity::class.java)
            }
        })
    }

    private fun checkAccountAndPassword() {
        tilAccount.error = ""
        tilPassword.error = ""
        val accountStr = tietAccount.text.toString()
        val passwordStr = tietPassword.text.toString()
        when {
            accountStr.isEmpty() -> tilAccount.error =
                getString(R.string.account_can_not_be_empty)
            passwordStr.isEmpty() -> tilPassword.error =
                getString(R.string.password_can_not_be_empty)
            else -> viewModel.login(accountStr,passwordStr)
        }
    }

    override fun handleError(errorStr: String) {
        super.handleError(errorStr)
    }
}