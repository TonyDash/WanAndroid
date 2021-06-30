package com.tonyDash.wanandroid.ui.main.mine

import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.fragment.BaseDataBindVMFragment
import com.cjy.baselibrary.fragment.BaseVMFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.commonlibrary.store.UserInfoStore
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.FragmentMineBinding
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment:BaseDataBindVMFragment<FragmentMineBinding>(){

    companion object {
        fun newInstance() = MineFragment()
    }
    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {
        llFlutter.setOnClickListener {
            startActivity(FlutterActivity.createDefaultIntent(mActivity))
        }
    }

    override fun initData() {
        mDataBind.isLogin = UserInfoStore.instance.isLogin()
        mDataBind.user = UserInfoStore.instance.getUserInfo()
    }
}