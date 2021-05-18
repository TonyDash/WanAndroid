package com.tonyDash.wanandroid.ui.main.home.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.ItemArticleBinding
import com.cjy.networklibrary.entity.Article

/**
 * 继承最简单的BaseQuickAdapter
 * 列表子view布局统一时使用
 */
class PopularAdapter : BaseQuickAdapter<Article, BaseDataBindingHolder<ItemArticleBinding>>(R.layout.item_article) {
    override fun convert(holder: BaseDataBindingHolder<ItemArticleBinding>, item: Article) {
        val dataBinding = holder.dataBinding
        addChildClickViewIds(R.id.iv_collect)
        dataBinding?.run {
            this.itemArticle = item
            this.executePendingBindings()
        }
    }
}