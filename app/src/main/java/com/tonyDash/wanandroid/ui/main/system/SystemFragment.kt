package com.tonyDash.wanandroid.ui.main.system

import android.widget.Toast
import com.cjy.baselibrary.fragment.BaseDataBindVMFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.FragmentHomeBinding
import com.tonyDash.wanandroid.ui.flutter.MyFlutterFragment
import kotlinx.android.synthetic.main.fragment_system.*

class SystemFragment : BaseDataBindVMFragment<FragmentHomeBinding>() {

    companion object {
        fun newInstance() = SystemFragment()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_system

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {
        btnTop.setOnClickListener {
            Toast.makeText(mActivity,"btnTop",Toast.LENGTH_SHORT).show()
        }
//        childFragmentManager.beginTransaction().replace(
//            R.id.flContainer,
//            MyFlutterFragment(mActivity, routePage = "/test").getFlutterFragment()
//        ).commit()
        fragmentManager?.run {
            beginTransaction().replace(
                R.id.flContainer,
                MyFlutterFragment(mActivity, routePage = "/test").getFlutterFragment()
            ).commit()
        }
    }

    override fun initData() {

    }
}