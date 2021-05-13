package com.tonyDash.wanandroid.room.repository

import com.tonyDash.wanandroid.room.dao.ReadHistoryDao
import com.tonyDash.wanandroid.ui.main.home.model.Article
import com.tonyDash.wanandroid.ui.main.home.model.Tag

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