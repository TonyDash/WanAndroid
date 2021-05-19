package com.cjy.networklibrary.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cjy.networklibrary.room.converters.UserConverters

@Entity
@TypeConverters(UserConverters::class)
data class UserInfo(
    @PrimaryKey
    val id: Int = 0,
    val admin: Boolean = false,
    val email: String = "",
    val icon: String = "",
    val nickname: String = "",
    val password: String = "",
    val publicName: String = "",
    val token: String = "",
    val type: Int = 0,
    val username: String = "",
    val collectIds: MutableList<Int> = mutableListOf(),
    val chapterTops: List<Any> = mutableListOf()
)