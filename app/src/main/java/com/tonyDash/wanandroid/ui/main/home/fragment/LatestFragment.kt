package com.tonyDash.wanandroid.ui.main.home.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cjy.baselibrary.autoService.myServiceLoader
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.fragment.BaseListMVFragment
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.commonlibrary.autoservice.IWebViewService
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.ui.main.home.adapter.LatestAdapter
import com.tonyDash.wanandroid.ui.main.home.adapter.binder.LatestBinder
import com.cjy.networklibrary.entity.Article
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
            val article = GsonUtil.instance.toJsonString(mAdapter.data[position])
            val webservice: IWebViewService? = myServiceLoader.load(IWebViewService::class.java)
            webservice?.run {
                this.startWebViewActivity(mActivity, mapOf(IWebViewService.PARAM_ARTICLE to article))
            }
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