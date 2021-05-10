package com.cjy.baselibrary.model
import com.cjy.baselibrary.utils.GsonUtil
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

open class CommonModel {

    companion object {

        fun <T> fromJson(json: String, clazz: Class<T>): T? {
            return GsonUtil.instance.parse(json,clazz)
        }

        fun <T> fromJson(json: String?, tClass: Class<*>, collectionClass: Class<*>?): T? {
            val type: Type = TypeToken.getParameterized(collectionClass, tClass).type
            return GsonUtil.instance.parse(json, type)
        }
    }

    fun toJson(): String {
        return GsonUtil.instance.toJsonString(this)
    }
}