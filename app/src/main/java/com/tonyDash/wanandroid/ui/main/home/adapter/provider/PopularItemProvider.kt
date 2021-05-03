package com.tonyDash.wanandroid.ui.main.home.adapter.provider

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.cjy.baselibrary.AppContext
import com.tonyDash.wanandroid.R
import com.tonyDash.wanandroid.databinding.ItemArticleBinding
import com.tonyDash.wanandroid.ui.main.home.model.Article

class PopularItemProvider : BaseItemProvider<Article>() {
    override val itemViewType: Int
        get() = 0
    override val layoutId: Int
        get() = R.layout.item_article

    override fun convert(helper: BaseViewHolder, item: Article) {
        val itemArticleBinding: ItemArticleBinding? = DataBindingUtil.getBinding(helper.itemView)
        itemArticleBinding?.run {
            this.itemArticle = item
            this.executePendingBindings()
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: Article, position: Int) {
        super.onClick(helper, view, data, position)
        Log.d("PopularItemProvider", "onClick")
    }

    override fun onLongClick(
        helper: BaseViewHolder,
        view: View,
        data: Article,
        position: Int
    ): Boolean {
        Log.d("PopularItemProvider", "onLongClick")
        return super.onLongClick(helper, view, data, position)
    }
}