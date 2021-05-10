package com.cjy.webviewlibrary.utils

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

class IntentUtil {
    companion object {
        @JvmStatic
        fun addParams(intent: Intent, extras: MutableMap<String, *>): Intent {
            if (extras.isEmpty()) return intent
            extras.forEach { (key, value) ->
                when (value) {
                    is Int -> intent.putExtra(key, value)
                    is Byte -> intent.putExtra(key, value)
                    is Char -> intent.putExtra(key, value)
                    is Long -> intent.putExtra(key, value)
                    is Float -> intent.putExtra(key, value)
                    is Short -> intent.putExtra(key, value)
                    is Double -> intent.putExtra(key, value)
                    is Boolean -> intent.putExtra(key, value)
                    is Bundle -> intent.putExtra(key, value)
                    is String -> intent.putExtra(key, value)
                    is IntArray -> intent.putExtra(key, value)
                    is ByteArray -> intent.putExtra(key, value)
                    is CharArray -> intent.putExtra(key, value)
                    is LongArray -> intent.putExtra(key, value)
                    is FloatArray -> intent.putExtra(key, value)
                    is ShortArray -> intent.putExtra(key, value)
                    is DoubleArray -> intent.putExtra(key, value)
                    is BooleanArray -> intent.putExtra(key, value)
                    is CharSequence -> intent.putExtra(key, value)
                    is Parcelable -> intent.putExtra(key, value)
                    is Array<*> -> {
                        when {
                            value.isArrayOf<String>() ->
                                intent.putExtra(key, value)
                            value.isArrayOf<Parcelable>() ->
                                intent.putExtra(key, value)
                            value.isArrayOf<CharSequence>() ->
                                intent.putExtra(key, value)
                            else -> intent.putExtra(key, value)
                        }
                    }
                }
            }
            return intent;
        }
    }
}