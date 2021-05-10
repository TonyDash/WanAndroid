package com.cjy.baselibrary.autoService

import java.util.ServiceLoader

class myServiceLoader private constructor(){

    companion object{

        fun <S> load(service: Class<S>): S? {
            return try {
                ServiceLoader.load(service).iterator().next()
            } catch (e: Exception) {
                null
            }
        }
    }
}