package com.example.departmentapp.domain.usecase

import com.example.core.departmentModel.DepartmentUiModel
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.di.repository.DepartmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val departmentRepository: DepartmentRepository
) {

    suspend operator fun invoke(department: DepartmentUiModel): Flow<List<ProductBaseUiModel>> {
        return departmentRepository.getProducts(department)
    }
}
