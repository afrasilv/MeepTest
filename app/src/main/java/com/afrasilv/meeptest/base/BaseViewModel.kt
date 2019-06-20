package com.afrasilv.meeptest.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.afrasilv.meeptest.extensions.KoinComponent
import com.afrasilv.meeptest.extensions.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel<T : BaseViewData>
    : ViewModel(), LifecycleObserver, CoroutineScope, KoinComponent {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cancelJob() {
        job.cancel()
    }

    protected abstract val mViewData: T
    fun getViewData() = mViewData

    protected val mViewCommand: SingleLiveEvent<BaseViewCommand> = SingleLiveEvent()
    fun getViewCommand() = mViewCommand
}