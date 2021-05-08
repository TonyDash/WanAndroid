package com.tonyDash.wanandroid.ui.main.home.adapter.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.tonyDash.wanandroid.databinding.ItemCategorySubBinding
import com.tonyDash.wanandroid.ui.main.home.model.Category

class CategoryBinder:QuickDataBindingItemBinder<Category,ItemCategorySubBinding>() {
    override fun convert(holder: BinderDataBindingHolder<ItemCategorySubBinding>, data: Category) {
        val dataBinding = holder.dataBinding
        dataBinding.category = data
        dataBinding.executePendingBindings()
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemCategorySubBinding {
        return ItemCategorySubBinding.inflate(layoutInflater,parent,false)
    }

}