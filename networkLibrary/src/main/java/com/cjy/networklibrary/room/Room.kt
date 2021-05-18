package com.cjy.networklibrary.room

import androidx.room.Room
import com.cjy.baselibrary.AppContext

private const val DB_NAME = "database_wanandroid"

val room = Room.databaseBuilder(AppContext, AppDataBase::class.java, DB_NAME).build()

val userDao = room.getUserDao()

val historyDao = room.readHistoryDao()