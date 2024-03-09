package com.example.departmentapp.domain.usecase

import com.example.core.departmentModel.DepartmentUiModel
import com.example.di.repository.DepartmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDepartmentUseCase @Inject constructor(
    private val departmentRepository: DepartmentRepository
) {

    suspend operator fun invoke(): Flow<List<DepartmentUiModel>>{
        return departmentRepository.getDepartments()
    }
}
