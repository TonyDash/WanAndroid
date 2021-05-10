package com.cjy.baselibrary.utils

import android.app.Activity
import android.content.Intent
import com.cjy.baselibrary.baseExt.putExtras

object ActivityManager {

    val activities = mutableListOf<Activity>()

    fun start(clazz: Class<out Activity>, params: Map<String, Any> = emptyMap()) {
        val currentActivity = activities[activities.lastIndex]
        val intent = Intent(currentActivity, clazz)
        params.forEach {
            intent.putExtras(it.key to it.value)
        }
        currentActivity.startActivity(intent)
    }

    /**
     * finish指定的一个或多个Activity
     */
    fun finish(vararg clazz: Class<out Activity>) {
        activities.forEach { activity ->
            if (clazz.contains(activity::class.java)) {
                activity.finish()
            }
        }
    }

}
