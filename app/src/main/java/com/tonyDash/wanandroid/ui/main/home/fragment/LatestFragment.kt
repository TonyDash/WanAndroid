package com.tonyDash.wanandroid.ui.main.home.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.fragment.BaseListMVFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.ui.main.home.adapter.LatestAdapter
import com.tonyDash.wanandroid.ui.main.home.adapter.binder.LatestBinder
import com.tonyDash.wanandroid.ui.main.home.model.Article
import com.tonyDash.wanandroid.ui.main.home.viewmodel.LatestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestFragment : BaseListMVFragment<Article>() {

    private val viewModel: LatestViewModel by viewModel()

    private val mAdapter = LatestAdapter()

    override fun getViewModel(): BaseViewModel = viewModel

    companion object {
        fun newInstance() = LatestFragment()
    }

    override fun initRecyclerView() {
        val mRecyclerView = mRootView?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.run {
            mAdapter.addItemBinder(Article::class.java, LatestBinder())
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(mActivity)
            mAdapter.setList(viewModel.articleList.value)
        }
        mAdapter.addChildClickViewIds(R.id.iv_collect)
        mAdapter.setOnItemClickListener { adapter, view, position ->
        }
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
        }
    }

    override fun initData() {
        viewModel.articleList.observe(this, mListObserver)
        initViewModelAction()
    }

    override fun getListData() {
        viewModel.getListData()
    }

    override fun refreshSuccess() {
        mAdapter.setList(viewModel.articleList.value)
    }

    override fun loadMoreSuccess() {
        mAdapter.setList(viewModel.articleList.value)
    }
}