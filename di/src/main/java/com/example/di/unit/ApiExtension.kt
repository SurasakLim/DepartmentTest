package com.example.di.unit

import android.util.Log
import com.example.core.basemodel.BaseUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

suspend fun <T> apiCall(apiCall: suspend () -> Response<T>): T? {
    try {
        val response = apiCall()
        val responseBody = response.body()
        if (response.isSuccessful) {
            return responseBody
        } else {
            throw ErrorMapper().map(response.errorBody())
        }
    } catch (exception: Exception) {
        Log.e("API-ERROR", "${exception.message}")
        throw ErrorMapper().map(exception)
    }
}

suspend fun <T> toApiCallFlow(call: suspend () -> Response<T>): Flow<BaseUiState<T>> {
    return flow {
        emit(BaseUiState.Loading(null, true))

        try {
            call().let { c ->
                try {
                    if (c.isSuccessful) {
                        c.body()?.let {
                            emit(BaseUiState.Success(it))
                        } ?: emit(BaseUiState.SuccessNonBody)
                    } else {
                        emit(BaseUiState.Error(ErrorMapper().map(c.errorBody())))
                    }
                } catch (e: Exception) {
                    emit(BaseUiState.Error(ErrorMapper().map(c.errorBody())))
                }
            }
            emit(BaseUiState.Loading(null, false))
        } catch (e: Exception) {
            emit(BaseUiState.Error(ErrorMapper().map(call().errorBody())))
        }

    }.flowOn(Dispatchers.IO)
}
