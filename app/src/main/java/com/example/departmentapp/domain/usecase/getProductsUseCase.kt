package com.example.departmentapp.domain.usecase

import com.example.core.departmentModel.DepartmentUiModel
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.departmentapp.common.BaseUseCase
import com.example.di.provide.repository.CoroutinesDispatchersModule
import com.example.di.repository.DepartmentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    @CoroutinesDispatchersModule.IoDispatcher
    private val coroutineDispatcher: CoroutineDispatcher,
    private val departmentRepository: DepartmentRepository
) : BaseUseCase<Flow<List<ProductBaseUiModel>>, DepartmentUiModel>(coroutineDispatcher) {

    override suspend fun run(params: DepartmentUiModel): Flow<List<ProductBaseUiModel>> {
        return departmentRepository.getProducts(params)
    }
}
