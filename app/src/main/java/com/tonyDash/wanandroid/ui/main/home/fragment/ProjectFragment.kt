package com.tonyDash.wanandroid.ui.main.home.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.cjy.baselibrary.fragment.BaseCategoryListMVFragment
import com.cjy.baselibrary.viewModel.BaseViewModel
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.ui.main.home.adapter.binder.CategoryBinder
import com.tonyDash.wanandroid.ui.main.home.adapter.binder.ProjectBinder
import com.tonyDash.wanandroid.ui.main.home.model.Article
import com.tonyDash.wanandroid.ui.main.home.model.Category
import com.tonyDash.wanandroid.ui.main.home.viewmodel.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectFragment:BaseCategoryListMVFragment<Category,Article>() {

    private val viewModel:ProjectViewModel by viewModel()
    private val categoryAdapter = BaseBinderAdapter()
    private val articleAdapter = BaseBinderAdapter()

    companion object {
        fun newInstance() = ProjectFragment()
    }
    override fun initCategoryRecyclerView() {
        val rvCategory = mRootView?.findViewById<RecyclerView>(R.id.rvCategory)
        rvCategory?.run {
            categoryAdapter.addItemBinder(Category::class.java,CategoryBinder())
            this.adapter = categoryAdapter
            categoryAdapter.setList(viewModel.categories.value)
            categoryAdapter.setOnItemClickListener { _, _, position ->
                viewModel.refreshProjectList(position)
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
    }

    override fun getListData() {
        mIsRefresh.yes {
            viewModel.refreshProjectList()
        }.otherwise {
            viewModel.loadMoreProjectList()
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
        viewModel.getProjectCategory()
    }

}