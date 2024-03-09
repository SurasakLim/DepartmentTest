package com.example.core.departmentModel
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName

@Keep
data class DepartmentResponseItem(
    @SerializedName("id")
    val id: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String?
)