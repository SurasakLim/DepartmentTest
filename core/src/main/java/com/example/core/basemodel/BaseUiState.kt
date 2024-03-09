package com.example.core.basemodel

sealed class BaseUiState<out T>(
    val status: ApiStatus,
    val data: T?,
    val apiException: Throwable?
) {
    object Non : BaseUiState<Nothing>(
        status = ApiStatus.NON,
        data = null,
        apiException = null
    )

    data class Success<out R>(val _data: R) : BaseUiState<R>(
        status = ApiStatus.SUCCESS,
        data = _data,
        apiException = null
    )

    object SuccessNonBody : BaseUiState<Nothing>(
        status = ApiStatus.SUCCESS,
        data = null,
        apiException = null
    )

    data class Error(val exception: Throwable) : BaseUiState<Nothing>(
        status = ApiStatus.ERROR,
        data = null,
        apiException = exception
    )

    data class Loading<out R>(val _data: R? = null, val isLoading: Boolean) : BaseUiState<R>(
        status = ApiStatus.LOADING,
        data = _data,
        apiException = null
    )
}

enum class ApiStatus {
    NON,
    SUCCESS,
    ERROR,
    LOADING
}
