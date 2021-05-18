package com.tonyDash.wanandroid.ui.main.home.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import com.cjy.baselibrary.autoService.myServiceLoader
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.fragment.BaseCategoryListMVFragment
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.cjy.commonlibrary.autoservice.IWebViewService
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.ui.main.home.adapter.binder.CategoryBinder
import com.tonyDash.wanandroid.ui.main.home.adapter.binder.ProjectBinder
import com.cjy.networklibrary.entity.Article
import com.cjy.networklibrary.entity.Category
import com.tonyDash.wanandroid.ui.main.home.viewmodel.WeChatViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeChatFragment:BaseCategoryListMVFragment<Category,Article>() {

    private val viewModel:WeChatViewModel by viewModel()
    private val categoryAdapter = BaseBinderAdapter()
    private val articleAdapter = BaseBinderAdapter()

    companion object {
        fun newInstance() = WeChatFragment()
    }
    override fun initCategoryRecyclerView() {
        val rvCategory = mRootView?.findViewById<RecyclerView>(R.id.rvCategory)
        rvCategory?.run {
            categoryAdapter.addItemBinder(Category::class.java,CategoryBinder())
            this.adapter = categoryAdapter
            categoryAdapter.setList(viewModel.categories.value)
            categoryAdapter.setOnItemClickListener { _, _, position ->
                viewModel.refreshWeChatList(position)
                categoryAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun initRecyclerView() {
        val rvArticle = mRootView?.findViewById<RecyclerView>(R.id.mRecyclerView)
        rvArticle?.run {
            articleAdapter.addItemBinder(Article::class.java,ProjectBinder())
            this.adapter = articleAdapter
            this.layoutManager = LinearLayoutManager(mActivity)
            articleAdapter.setList(viewModel.articleList.value)
        }
        articleAdapter.addChildClickViewIds(R.id.iv_collect)
        articleAdapter.setOnItemClickListener { _, _, position ->
            val article = GsonUtil.instance.toJsonString(articleAdapter.data[position])
            val webservice: IWebViewService? = myServiceLoader.load(IWebViewService::class.java)
            webservice?.run {
                this.startWebViewActivity(mActivity, mapOf(IWebViewService.PARAM_ARTICLE to article))
            }
        }
        articleAdapter.setOnItemChildClickListener { adapter, view, position ->
        }
    }

    override fun getListData() {
        mIsRefresh.yes {
            viewModel.refreshWeChatList()
        }.otherwise {
            viewModel.loadMoreWeChatList()
        }
    }

    override fun loadCategorySuccess() {
        categoryAdapter.setList(viewModel.categories.value)
    }

    override fun refreshSuccess() {
        articleAdapter.setList(viewModel.articleList.value)
    }

    override fun loadMoreSuccess() {
        articleAdapter.setList(viewModel.articleList.value)
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun initData() {
        viewModel.categories.observe(this,mCategoryObserver)
        viewModel.articleList.observe(this, mListObserver)
        viewModel.getWeChatCategory()
    }

}