package com.tonyDash.wanandroid.ui.main.common.repository

import com.cjy.networklibrary.entity.Article
import com.cjy.networklibrary.entity.Tag
import com.cjy.networklibrary.room.dao.ReadHistoryDao

class HistoryRepository(private val dao: ReadHistoryDao) {

    suspend fun queryAllReadHistory() = dao.queryAll()
        .map { it.article.apply { tags = it.tags } }.reversed()

    suspend fun addReadHistory(article: Article) {
        dao.queryArticle(article.id)?.let {
            dao.deleteArticle(it)
        }
        dao.insert(article.apply { primaryKeyId = 0 })
        article.tags.forEach {
            dao.insertArticleTag(
                Tag(id = 0, articleId = article.id.toLong(), name = it.name, url = it.url)
            )
        }
    }
    suspend fun deleteReadHistory(article: Article) = dao.deleteArticle(article)
}