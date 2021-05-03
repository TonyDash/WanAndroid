package com.tonyDash.wanandroid.ui.main.home

import androidx.fragment.app.Fragment
import com.cjy.baselibrary.adapter.SimpleFragmentPagerAdapter
import com.cjy.baselibrary.fragment.BaseDataBindVMFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.FragmentHomeBinding
import com.tonyDash.wanandroid.ui.main.home.fragment.LatestFragment
import com.tonyDash.wanandroid.ui.main.home.fragment.PopularFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseDataBindVMFragment<FragmentHomeBinding>() {

    private lateinit var fragments: List<Fragment>
    private var currentOffset = 0

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {
        fragments = listOf(
            PopularFragment.newInstance(),
            LatestFragment.newInstance()
        )
        val titles = listOf<CharSequence>(
            getString(R.string.popular_articles),
            getString(R.string.latest_project)
        )
        viewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager, fragments, titles)
        viewPager.offscreenPageLimit = fragments.size
        tabLayout.setupWithViewPager(viewPager)

//        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, offset ->
//            if (activity is MainActivity && this.currentOffset != offset) {
//                (activity as MainActivity).animateBottomNavigationView(offset > currentOffset)
//                currentOffset = offset
//            }
//        })
    }
}