package com.tonyDash.wanandroid.ui.main.home.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cjy.baselibrary.autoService.myServiceLoader
import com.cjy.baselibrary.fragment.BaseListMVFragment
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.commonlibrary.autoservice.IWebViewService
import com.cjy.commonlibrary.eventBus.LiveEventBus
import com.cjy.commonlibrary.utils.Constants
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.ui.main.home.adapter.PopularAdapter
import com.cjy.networklibrary.entity.Article
import com.tonyDash.wanandroid.ui.main.home.viewmodel.PopularViewModel
import com.tonyDash.wanandroid.ui.main.home.viewmodel.PopularViewModel.Companion.INITIAL_PAGE
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
            mAdapter.setList(viewModel.articleList.value)
        }
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val article = GsonUtil.instance.toJsonString(mAdapter.data[position])
            val webservice:IWebViewService? = myServiceLoader.load(IWebViewService::class.java)
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
        LiveEventBus.with<Pair<Int,Boolean>>(Constants.LIVE_EVENT_CHANGE_COLLECT).observe(viewLifecycleOwner,{
            viewModel.updateItemCollectState(it)
        })
    }

    override fun getListData() {
        viewModel.getListData()
    }

    override fun refreshSuccess() {
        viewModel.page = INITIAL_PAGE
        mAdapter.setList(viewModel.articleList.value)
    }

    override fun loadMoreSuccess() {
        mAdapter.setList(viewModel.articleList.value)
    }

    override fun getViewModel(): BaseViewModel = viewModel
}
