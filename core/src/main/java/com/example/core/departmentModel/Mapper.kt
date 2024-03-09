package com.example.core.departmentModel


fun List<DepartmentResponseItem>.mapToListUiModel(): List<DepartmentUiModel> {
    return map { it.mapToDepartmentUiModel() }
}

fun DepartmentResponseItem.mapToDepartmentUiModel(): DepartmentUiModel {
    return DepartmentUiModel(id = id, imageUrl = imageUrl, name = name)
}

fun List<Product>?.toUiModels(department: DepartmentUiModel): List<ProductBaseUiModel> {
    val productList = mutableListOf<ProductBaseUiModel>()
    productList.add(ProductBaseUiModel.ProductHeaderUiModel(name = department.name ?: ""))
    val productsUiModel = this?.map {
        it.toUiModel()
    } ?: emptyList()
    productList.addAll(productsUiModel)
    return productList
}

fun Product.toUiModel(): ProductBaseUiModel.ProductUiModel {
    return ProductBaseUiModel.ProductUiModel(
        departmentId = departmentId,
        desc = desc,
        id = id,
        imageUrl = imageUrl,
        name = name,
        price = price,
        type = type
    )
}