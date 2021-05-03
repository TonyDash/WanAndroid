package com.cjy.baselibrary.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.lang.Exception

//类型别名
typealias LaunchBlock = suspend CoroutineScope.() -> Unit

typealias EmitBlock<T> = suspend LiveDataScope<T>.() -> T

typealias AsyncBlock<T> = suspend () -> T

typealias Cancel = suspend (e: Exception) -> Unit

typealias Error = suspend (e: Exception) -> Unit

open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<StateActionEvent>()//通用事件模型驱动(如：显示对话框、取消对话框、错误提示)


    /***
     * 创建协程，不需要返回值
     */
    fun launch(cancel: Cancel? = null, block: LaunchBlock) {//使用协程封装统一的网络请求处理
        viewModelScope.launch {
            //ViewModel自带的viewModelScope.launch,会在页面销毁的时候自动取消请求,有效封装内存泄露
            try {
                mStateLiveData.value = LoadState
                block()
                mStateLiveData.value = SuccessState
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> cancel?.invoke(e)
                    else -> mStateLiveData.value = ErrorState(e.message)
                }
            }
        }
    }

    /**
     * 根据泛型，返回结果
     */
    fun <T> emit(cancel: Cancel? = null, block: EmitBlock<T>): LiveData<T> = liveData {
        try {
            mStateLiveData.value = LoadState
            emit(block())
            mStateLiveData.value = SuccessState
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> cancel?.invoke(e)
                else -> mStateLiveData.value = ErrorState(e.message)
            }
        }
    }

    /***
     * 用于需要多步调用的场景
     * 例如一个界面的数据显示，需要调用多个接口，由于协程是按照顺序执行的，多个接口的顺序调用
     * 会导致耗时增多，所以需要通过async来进行同时请求。
     * 结果要用await来调用获取
     */
    protected fun <T> async(block: AsyncBlock<T>): Deferred<T> {
        return viewModelScope.async { block.invoke() }
    }
}