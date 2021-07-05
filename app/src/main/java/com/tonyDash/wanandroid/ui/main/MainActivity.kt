package com.tonyDash.wanandroid.ui.main

import androidx.fragment.app.Fragment
import com.cjy.baselibrary.activity.BaseDataBindingActivity
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.ActivityMainBinding
import com.tonyDash.wanandroid.ui.flutter.MyFlutterFragment
import com.tonyDash.wanandroid.ui.main.discovery.DiscoveryFragment
import com.tonyDash.wanandroid.ui.main.home.HomeFragment
import com.tonyDash.wanandroid.ui.main.mine.MineFragment
import com.tonyDash.wanandroid.ui.main.navigation.NavigationFragment
import com.tonyDash.wanandroid.ui.main.system.SystemFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseDataBindingActivity<ActivityMainBinding>() {

    private lateinit var fragments: Map<Int, Fragment>

    override fun initData() {
    }

    override fun initViews() {
        fragments = mapOf(
            R.id.home to createFragment(HomeFragment::class.java),
            R.id.system to createFragment(MyFlutterFragment::class.java),
            R.id.discovery to createFragment(DiscoveryFragment::class.java),
            R.id.navigation to createFragment(NavigationFragment::class.java),
            R.id.mine to createFragment(MineFragment::class.java)
        )

        bottomNavigationView.run {
            setOnNavigationItemSelectedListener { menuItem ->
                showFragment(menuItem.itemId)
                true
            }
            setOnNavigationItemReselectedListener { menuItem ->
                val fragment = fragments.entries.find { it.key == menuItem.itemId }?.value
//                if (fragment is ScrollToTop) {
//                    fragment.scrollToTop()
//                }
            }
        }

//        if (savedInstanceState == null) {
            val initialItemId = R.id.home
            bottomNavigationView.selectedItemId = initialItemId
            showFragment(initialItemId)
//        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    private fun createFragment(clazz: Class<out Fragment>): Fragment {
        var fragment = supportFragmentManager.fragments.find { it.javaClass == clazz }
        if (fragment == null) {
            fragment = when (clazz) {
                HomeFragment::class.java -> HomeFragment.newInstance()
                MyFlutterFragment::class.java -> MyFlutterFragment(this,routePage = "/test").getFlutterFragment()
                DiscoveryFragment::class.java -> DiscoveryFragment.newInstance()
                NavigationFragment::class.java -> NavigationFragment.newInstance()
                MineFragment::class.java -> MineFragment.newInstance()
                else -> throw IllegalArgumentException("argument ${clazz.simpleName} is illegal")
            }
        }
        return fragment
    }

    private fun showFragment(menuItemId: Int) {
        val currentFragment = supportFragmentManager.fragments.find {
            it.isVisible && it in fragments.values
        }
        val targetFragment = fragments.entries.find { it.key == menuItemId }?.value
        supportFragmentManager.beginTransaction().apply {
            currentFragment?.let { if (it.isVisible) hide(it) }
            targetFragment?.let {
                if (it.isAdded) show(it) else add(R.id.flContent, it)
            }
        }.commit()
    }
}
