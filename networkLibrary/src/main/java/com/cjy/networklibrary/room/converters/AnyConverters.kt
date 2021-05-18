package com.cjy.networklibrary.room.converters

import androidx.room.TypeConverter
import com.cjy.baselibrary.utils.GsonUtil
import com.google.gson.reflect.TypeToken

class UserConverters {
    @TypeConverter
    fun stringToList(value: String): List<Any> {
        val listType = object : TypeToken<List<Any>>() {

        }.type
        return GsonUtil.instance.parse(value, listType)?: mutableListOf()
    }

    @TypeConverter
    fun listToString(list: List<Any>): String {
        return GsonUtil.instance.toJsonString(list)
    }
    @TypeConverter
    fun stringToMutableList(value: String): MutableList<Int> {
        val listType = object : TypeToken<MutableList<Int>>() {

        }.type
        return GsonUtil.instance.parse(value, listType)?: mutableListOf()
    }

    @TypeConverter
    fun mutableListToString(list: MutableList<Int>): String {
        return GsonUtil.instance.toJsonString(list)
    }
}