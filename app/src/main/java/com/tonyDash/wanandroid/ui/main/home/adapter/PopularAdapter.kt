package com.tonyDash.wanandroid.ui.main.home.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.ItemArticleBinding
import com.tonyDash.wanandroid.ui.main.home.model.Article

class PopularAdapter : BaseQuickAdapter<Article, BaseDataBindingHolder<ItemArticleBinding>>(R.layout.item_article) {
    override fun convert(holder: BaseDataBindingHolder<ItemArticleBinding>, item: Article) {
        val dataBinding = holder.dataBinding
        dataBinding?.run {
            this.itemArticle = item
            this.executePendingBindings()
        }
    }
}