package com.tonyDash.wanandroid.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tonyDash.wanandroid.room.dao.ReadHistoryDao
import com.tonyDash.wanandroid.room.dao.UserDao
import com.tonyDash.wanandroid.ui.main.home.model.Article
import com.tonyDash.wanandroid.ui.main.home.model.Tag
import com.tonyDash.wanandroid.ui.main.mine.model.UserInfo


@Database(entities = [UserInfo::class,Article::class,Tag::class],version = 1,exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun readHistoryDao(): ReadHistoryDao
}