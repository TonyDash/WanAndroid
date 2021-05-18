package com.tonyDash.wanandroid.ui.main.home.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import com.cjy.baselibrary.autoService.myServiceLoader
import com.cjy.baselibrary.fragment.BaseListMVFragment
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.commonlibrary.autoservice.IWebViewService
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.ui.main.home.adapter.binder.SquareBinder
import com.cjy.networklibrary.entity.Article
import com.tonyDash.wanandroid.ui.main.home.viewmodel.SquareViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SquareFragment:BaseListMVFragment<Article>() {

    private val viewModel:SquareViewModel by viewModel()
    private val mAdapter = BaseBinderAdapter()

    companion object {
        fun newInstance() = SquareFragment()
    }

    override fun initRecyclerView() {
        val mRecyclerView = mRootView?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.run {
            mAdapter.addItemBinder(Article::class.java, SquareBinder())
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(mActivity)
            mAdapter.setList(viewModel.articleList.value)
        }
        mAdapter.addChildClickViewIds(R.id.iv_collect)
        mAdapter.setOnItemClickListener { _, _, position ->
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

    override fun refreshSuccess() {
        mAdapter.setList(viewModel.articleList.value)
    }

    override fun loadMoreSuccess() {
        mAdapter.setList(viewModel.articleList.value)
    }


    override fun getListData() {
        viewModel.getListData()
    }

    override fun getViewModel(): BaseViewModel = viewModel
}