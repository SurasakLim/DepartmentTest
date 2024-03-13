package com.example.departmentapp.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<Type, in Params>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(scope: CoroutineScope, params: Params, onResult: (Type) -> Unit = {}) {
        scope.launch {
            val result = withContext(coroutineDispatcher) {
                run(params)
            }
            onResult(result)
        }
    }
}
