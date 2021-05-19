package com.cjy.commonlibrary.eventBus

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

object LiveEventBus {
    private val bus: MutableMap<String, LiveEvent<Any>> = mutableMapOf()
    var enable = true

    private val mainHandler = Handler(Looper.getMainLooper())


    // kotlin method
    inline fun <reified T> with(eventKey: String, observeLastVersionValue: Boolean = false): MutableLiveData<T> {
        return getEvent<T>(eventKey, observeLastVersionValue).busData as MutableLiveData<T>
    }

    // java method
    fun <T> bind(eventKey: String, observeLastVersionValue: Boolean = false): MutableLiveData<T> {
        return getEvent<T>(eventKey, observeLastVersionValue).busData as MutableLiveData<T>
    }

    fun <T> getEvent(eventKey: String, observeLastVersionValue: Boolean): LiveEvent<Any> {
        var currentEvent: LiveEvent<Any>?
        if (bus.containsKey(eventKey)) {
            currentEvent = bus[eventKey]
            if (currentEvent == null) {
                currentEvent = LiveEvent<Any>(eventKey, observeLastVersionValue).apply {
                    bus[eventKey] = this
                }
            }
        } else {
            currentEvent = LiveEvent<Any>(eventKey, observeLastVersionValue).apply {
                bus[eventKey] = this
            }
        }
        return currentEvent
    }

    fun post(eventKey: String, value: Any) {
        if (!enable) return
        bus[eventKey]?.post(value)
    }

    fun remove(eventKey: String) {
        bus[eventKey]?.let {
            if (!it.busData.hasObservers()) {
                bus.remove(eventKey)
            }
        }
    }

    fun clear() {
        bus.clear()
    }

    class LiveEvent<Any>(eventKey: String, observeLastVersionValue: Boolean) {
        val busData: LiveBusData<Any> = LiveBusData(eventKey, this, observeLastVersionValue)
        // 预留位置后续需要做一些特殊情况支持
        var version: Int = 1

        @Synchronized
        fun post(value: Any) {
            version++

            if (isMainThread()) {
                busData.value = value
            } else {
                mainHandler.post {
                    busData.value = value
                }
                // 源码里面虽然会帮你切换主线程，但是临时保存逻辑上会覆盖之前的数据
//                busData.postValue(value)
            }
        }

        inner class LiveBusData<T>(private val eventKey: String,
                                   private val event: LiveEvent<T>,
                                   private val sticky: Boolean) : MutableLiveData<T>() {


            override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
                super.observe(owner, ObserverWrapper(observer, event, sticky))
            }

            override fun observeForever(observer: Observer<in T>) {
                super.observeForever(observer)
            }

            override fun removeObserver(observer: Observer<in T>) {
                super.removeObserver(observer)
                remove(eventKey)
            }

            override fun removeObservers(owner: LifecycleOwner) {
                super.removeObservers(owner)
            }
        }
    }

    class ObserverWrapper<T>(
        private val observer: Observer<in T>,
        private val event: LiveEvent<T>,
        private val observeLastVersionValue: Boolean) : Observer<T> {

        private var lastVersion = event.version

        override fun onChanged(t: T?) {
            if (lastVersion >= event.version) {
                if (observeLastVersionValue) {
                    observer.onChanged(t)
                }
                return
            }
            lastVersion = event.version

            observer.onChanged(t)
        }

    }

    private fun isMainThread(): Boolean {
//        return Thread.currentThread() == Looper.getMainLooper().thread
        return Looper.myLooper() == Looper.getMainLooper()
    }
}