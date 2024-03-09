package com.example.di.repository

import com.example.core.departmentModel.DepartmentUiModel
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.core.departmentModel.mapToListUiModel
import com.example.core.departmentModel.toUiModels
import com.example.di.provide.departmentApi.DepartmentApi
import com.example.di.unit.apiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

interface DepartmentRepository {
    suspend fun getDepartments(): Flow<List<DepartmentUiModel>>

    suspend fun getProducts(department: DepartmentUiModel): Flow<List<ProductBaseUiModel>>
}

@Singleton
class DepartmentRepositoryImpl @Inject constructor(
    private val departmentApi: DepartmentApi
) : DepartmentRepository {

    override suspend fun getDepartments(): Flow<List<DepartmentUiModel>> {
        return flow {
            apiCall {
                departmentApi.getDepartmentCarousel().also {
                    if (it.isSuccessful && it.body() != null) {
                        it.body()?.mapToListUiModel()?.let { department ->
                            emit(department)
                        }
                    }
                }
            }
        }
    }

    override suspend fun getProducts(department: DepartmentUiModel): Flow<List<ProductBaseUiModel>> {
        return flow {
            apiCall {
                departmentApi.getProductList(department.id ?: "").also {
                    if (it.isSuccessful && it.body() != null) {
                        it.body()?.toUiModels(department)?.let { product ->
                            emit(product)
                        }
                    }
                }
            }
        }
    }
}
