package com.tonyDash.wanandroid.ui.main.home.fragment

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.fragment.BaseListMVFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.ui.main.home.adapter.PopularAdapter
import com.tonyDash.wanandroid.ui.main.home.model.Article
import com.tonyDash.wanandroid.ui.main.home.viewmodel.PopularViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : BaseListMVFragment<Article>() {

    private val viewModel: PopularViewModel by viewModel()
    private val mAdapter = PopularAdapter()

    companion object {
        fun newInstance() = PopularFragment()
    }

    override fun initRecyclerView() {
        val mRecyclerView = mRootView?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.run {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(mActivity)
            mAdapter.setList(mListData)
        }
        mAdapter.setOnItemClickListener { adapter, view, position ->
            Toast.makeText(mActivity, "itemClick $position", Toast.LENGTH_SHORT).show()
        }
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            Toast.makeText(mActivity, "itemChildClick $position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun initData() {
        viewModel.articleList.observe(this, mListObserver)
        initViewModelAction()
    }

    override fun getListData() {
        (mPage == 1).yes {
            viewModel.refreshArticleList()
        }.otherwise {
            viewModel.loadMoreArticleList()
        }
    }

    override fun refreshSuccess() {
        mAdapter.addData(mListData)
    }

    override fun loadMoreSuccess() {
        mAdapter.addData(mListData)
    }

    override fun getViewModel(): BaseViewModel = viewModel
}
