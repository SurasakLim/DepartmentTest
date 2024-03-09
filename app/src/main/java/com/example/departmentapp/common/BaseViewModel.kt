package com.example.departmentapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    fun <T> MutableSharedFlow<T>.emitOnSuspend(value: T) {
        launch { emit(value) }
    }

    fun MutableSharedFlow<Unit>.emitOnSuspend() {
        launch { emit(Unit) }
    }

    fun <T> launch(
        onError: (Throwable) -> Unit = {},
        onLaunch: () -> Unit = {},
        onSuccess: (T) -> Unit = {},
        suspendBlock: suspend () -> T
    ): Job {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }
        return viewModelScope
            .plus(exceptionHandler)
            .launch(Dispatchers.IO) {
                onLaunch()
                onSuccess(suspendBlock())
            }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}