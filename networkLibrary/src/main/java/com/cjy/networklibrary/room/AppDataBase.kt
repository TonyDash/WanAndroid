package com.cjy.networklibrary.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cjy.networklibrary.entity.Article
import com.cjy.networklibrary.entity.Tag
import com.cjy.networklibrary.entity.UserInfo
import com.cjy.networklibrary.room.dao.ReadHistoryDao
import com.cjy.networklibrary.room.dao.UserDao


@Database(entities = [UserInfo::class, Article::class, Tag::class],version = 1,exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun readHistoryDao(): ReadHistoryDao
}