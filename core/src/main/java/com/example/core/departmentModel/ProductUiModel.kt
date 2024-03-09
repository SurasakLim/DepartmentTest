package com.example.core.departmentModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ProductBaseUiModel : Parcelable {

    @Parcelize
    data class ProductHeaderUiModel(
        val name: String
    ) : ProductBaseUiModel()

    @Parcelize
    data class ProductUiModel(
        val departmentId: String?,
        val desc: String?,
        val id: String?,
        val imageUrl: String?,
        val name: String?,
        val price: String?,
        val type: String?
    ) : ProductBaseUiModel()
}
