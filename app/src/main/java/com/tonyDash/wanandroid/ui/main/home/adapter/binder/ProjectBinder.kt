package com.tonyDash.wanandroid.ui.main.home.adapter.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.tonyDash.wanandroid.databinding.ItemArticleBinding
import com.cjy.networklibrary.entity.Article

class ProjectBinder:QuickDataBindingItemBinder<Article,ItemArticleBinding>() {
    override fun convert(holder: BinderDataBindingHolder<ItemArticleBinding>, data: Article) {
        val dataBinding = holder.dataBinding
        dataBinding.itemArticle = data
        dataBinding.executePendingBindings()
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemArticleBinding {
        return ItemArticleBinding.inflate(layoutInflater,parent,false)
    }
}