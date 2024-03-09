package com.example.di.unit

import com.example.core.basemodel.ApiErrorResponse
import com.example.core.basemodel.ApiException
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.SocketTimeoutException

class ErrorMapper {

    fun map(throwable: Throwable?) = when (throwable) {
        is HttpException,
        is SocketTimeoutException,
        null -> UnknownException()

        else -> throwable
    }

    fun map(responseBody: ResponseBody?): Throwable {
        val apiException = try {
            val gsonDeserializer = JsonDeserializer { json, _, _ ->
                ApiErrorResponse(
                    code = json?.asJsonObject?.get("code")?.asInt,
                    message = json?.asJsonObject?.get("message")?.asString,
                    statusCode = json?.asJsonObject?.get("status")?.asInt,
                )
            }
            val response: ApiErrorResponse? = GsonBuilder()
                .registerTypeAdapter(ApiErrorResponse::class.java, gsonDeserializer)
                .create()
                .fromJson(responseBody?.string().orEmpty(), ApiErrorResponse::class.java)
            ApiException(
                errorCode = response?.code ?: response?.statusCode ?: -1,
                errorMessage = response?.message as? String
                    ?: "Something went wrong. Please try again later.",
                status = response?.statusCode ?: -1,
            )
        } catch (throwable: Throwable) {
            ApiException(
                errorCode = -1,
                errorMessage = "Something went wrong. Please try again later.",
                status = -1,
            )
        }
        return map(apiException)
    }

}

class UnknownException : Throwable("Something went wrong. Please try again later.")
