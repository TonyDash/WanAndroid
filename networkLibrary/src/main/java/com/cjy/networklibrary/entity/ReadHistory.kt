package com.cjy.networklibrary.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ReadHistory(
    @Embedded
    var article: Article,
    @Relation(parentColumn = "id", entityColumn = "article_id")
    var tags: List<Tag>
)