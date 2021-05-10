package com.cjy.baselibrary.utils

import android.text.TextUtils
import com.google.gson.Gson
import java.lang.reflect.Type


class GsonUtil private constructor() {

    companion object {
        val instance = Holder.holder
    }

    private val gson by lazy { Gson() }

    fun <T> parse(json: String, tClass: Class<T>): T? {
        if (TextUtils.isEmpty(json)) {
            return null
        }
        return gson.fromJson(json, tClass)
    }

    fun <T> parse(json: String?, type: Type): T? {
        if (TextUtils.isEmpty(json)) {
            return null
        }
        return gson.fromJson(json, type)
    }

    fun toJsonString(objectEntity: Any): String {
        return gson.toJson(objectEntity)
    }

    private object Holder {
        val holder = GsonUtil()
    }
}