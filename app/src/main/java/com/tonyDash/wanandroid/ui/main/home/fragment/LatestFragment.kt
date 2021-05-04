package com.tonyDash.wanandroid.ui.main.home.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.fragment.BaseListMVFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.ui.main.home.adapter.LatestAdapter
import com.tonyDash.wanandroid.ui.main.home.adapter.binder.LatestBinder
import com.tonyDash.wanandroid.ui.main.home.model.Article
import com.tonyDash.wanandroid.ui.main.home.viewmodel.LatestViewModel
import kotlinx.android.synthetic.main.common_refresh_recyclerview.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestFragment : BaseListMVFragment<Article>() {

    private val viewModel: LatestViewModel by viewModel()

    private val mAdapter = LatestAdapter()

    override fun getViewModel(): BaseViewModel = viewModel

    companion object {
        fun newInstance() = LatestFragment()
    }

    override fun initRecyclerView() {
        mRecyclerView?.run {
            mAdapter.addItemBinder(Article::class.java, LatestBinder())
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(mActivity)
            mAdapter.setList(mListData)
        }
    }

    override fun initData() {
        viewModel.articleList.observe(this, mListObserver)
        initViewModelAction()
    }

    override fun getListData() {
        (mPage == 1).yes {
            viewModel.refreshProjectList()
        }.otherwise {
            viewModel.loadMoreProjectList()
        }
    }

    override fun refreshSuccess() {
        mAdapter.addData(mListData)
    }

    override fun loadMoreSuccess() {
        mAdapter.addData(mListData)
    }
}