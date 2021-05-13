package com.tonyDash.wanandroid.ui.main.mine.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tonyDash.wanandroid.room.converter.UserConverters

@Entity
@TypeConverters(UserConverters::class)
data class UserInfo(
    @PrimaryKey
    val id: Int,
    val admin: Boolean,
    val email: String,
    val icon: String,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String,
    val collectIds: MutableList<Int> = mutableListOf(),
    val chapterTops: List<Any> = mutableListOf()
)