package com.example.core.basemodel

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ApiErrorResponse(
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: Any? = null,
    @SerializedName("status") val statusCode: Int? = null,
)