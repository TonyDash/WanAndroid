package com.tonyDash.wanandroid.ui.main.common.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.tonyDash.wanandroid.ui.main.home.model.Article
import com.tonyDash.wanandroid.ui.main.home.model.Tag

data class ReadHistory(
    @Embedded
    var article: Article,
    @Relation(parentColumn = "id", entityColumn = "article_id")
    var tags: List<Tag>
)