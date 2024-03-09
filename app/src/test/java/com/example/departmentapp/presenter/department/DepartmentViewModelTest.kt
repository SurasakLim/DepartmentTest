@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.departmentapp.presenter.department

import com.example.core.departmentModel.DepartmentUiModel
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.departmentapp.domain.usecase.GetDepartmentUseCase
import com.example.departmentapp.domain.usecase.GetProductsUseCase
import com.example.departmentapp.presenter.department.unit.MainCoroutineRule
import com.example.di.repository.DepartmentRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DepartmentViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DepartmentViewModel

    private val departmentRepository: DepartmentRepository = mockk()
    private lateinit var getDepartmentUseCase: GetDepartmentUseCase
    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setUp() {
        getDepartmentUseCase = GetDepartmentUseCase(departmentRepository)
        getProductsUseCase = GetProductsUseCase(departmentRepository)
        viewModel = DepartmentViewModel(getDepartmentUseCase, getProductsUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get department list has data`() = runTest {
        val departmentData =
            listOf(DepartmentUiModel(id = "1", imageUrl = "test-url", name = "test-name"))

        coEvery {
            getDepartmentUseCase()
        }.returns(flow { emit(departmentData) })

        viewModel.getDepartmentData()
        advanceUntilIdle()

        assertEquals(
            departmentData.first(),
            viewModel.uiState.value.first()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `select department productId has change data and fetch Product data`() = runTest {
        val departmentData =
            listOf(
                DepartmentUiModel(
                    id = "1",
                    imageUrl = "test-url",
                    name = "test-name",
                    isSelected = true
                ),
                DepartmentUiModel(id = "2", imageUrl = "test-url", name = "test-name")
            )
        val productData = listOf<ProductBaseUiModel>(
            ProductBaseUiModel.ProductUiModel(
                id = "1",
                name = "test-name-1",
                imageUrl = "test-image-1",
                desc = "test-desc-1",
                type = "",
                price = "",
                departmentId = ""
            )
        )

        coEvery {
            getDepartmentUseCase()
        }.returns(flow { emit(departmentData) })

        viewModel.getDepartmentData()
        advanceUntilIdle()
        async {
            viewModel.onSelectDepartment("2")
        }.await()
        advanceUntilIdle()

        coEvery {
            viewModel.uiState.value.find { it.isSelected }?.let {
                getProductsUseCase.invoke(it)
            }
        }.returns(flow { emit(productData) })

        viewModel.observerProductChange()
        viewModel.uiState.value.find { it.isSelected }?.let {
            viewModel.getProductByDepartment(it)
        }
        runCurrent()

        assertEquals(
            DepartmentUiModel(
                id = "2",
                imageUrl = "test-url",
                name = "test-name",
                isSelected = true
            ),
            viewModel.uiState.value[1]
        )
        assertEquals(
            productData.first(),
            viewModel.productUiState.value.first()
        )
    }

}
