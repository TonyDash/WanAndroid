package com.cjy.baselibrary.fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import com.cjy.baselibrary.R
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.kennyc.view.MultiStateView
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.common_refresh_recyclerview.*

/**
 * 分页列表页面封装
 * 界面数据为列表式，继承这个父类
 */
abstract class BaseCategoryListMVFragment<C,M> : BaseVMFragment(),
    OnRefreshListener, OnLoadMoreListener {

    protected val mListData = ObservableArrayList<M>()

    protected val mCategoryData = ObservableArrayList<C>()

    protected var mIsRefresh = true

    override fun getLayoutRes(): Int = R.layout.common_category_list_recyclerview

    override fun initView() {
        initCategoryRecyclerView()
        initRefreshLayout()
        initRecyclerView()
    }

    private fun initRefreshLayout() {
        mRefreshLayout.run {
            setRefreshHeader(ClassicsHeader(mActivity))
            setRefreshFooter(ClassicsFooter(mActivity))
            setOnRefreshListener(this@BaseCategoryListMVFragment)
            setOnLoadMoreListener(this@BaseCategoryListMVFragment)
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mIsRefresh = true
        initViewModelAction()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mIsRefresh = false
        initViewModelAction()
    }

    protected fun initViewModelAction() {
        mIsRefresh.yes {
            mRefreshLayout.autoRefreshAnimationOnly()
        }
        getListData()
    }

    protected val mCategoryObserver = Observer<List<C>>{
        (!it.isNullOrEmpty()).yes {
            mCategoryData.clear()
            mCategoryData.addAll(it)
            loadCategorySuccess()
            dismissLoading()
        }
    }

    protected val mListObserver = Observer<List<M>> {
        (it != null && it.isNotEmpty()).yes {
            mMultipleStatusView.viewState = MultiStateView.ViewState.CONTENT
        }
        mIsRefresh.yes {
            mListData.clear()
            mListData.addAll(it)
            refreshSuccess()
            mRefreshLayout.finishRefresh()
        }.otherwise {
            mListData.addAll(it)
            loadMoreSuccess()
            mRefreshLayout.finishLoadMore()
        }
    }

    override fun dismissLoading() {
        mRefreshLayout.run {
            finishRefresh()
            finishLoadMore()
        }
    }

    abstract fun initCategoryRecyclerView()
    abstract fun initRecyclerView()
    abstract fun getListData()
    abstract fun refreshSuccess()
    abstract fun loadMoreSuccess()
    abstract fun loadCategorySuccess()
}