
package com.example.core.basemodel

class ApiException(
    val errorCode: Int,
    val errorMessage: String,
    val status: Int,
) : Throwable(
    "$errorMessage [$errorCode]"
)