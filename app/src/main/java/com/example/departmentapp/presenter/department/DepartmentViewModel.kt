package com.example.departmentapp.presenter.department

import androidx.lifecycle.viewModelScope
import com.example.core.departmentModel.DepartmentUiModel
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.departmentapp.common.BaseViewModel
import com.example.departmentapp.domain.usecase.GetDepartmentUseCase
import com.example.departmentapp.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
    private val getDepartmentUseCase: GetDepartmentUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<List<DepartmentUiModel>>(emptyList())

    val uiState: StateFlow<List<DepartmentUiModel>>
        get() = _uiState

    private val _productUiState = MutableStateFlow<List<ProductBaseUiModel>>(emptyList())

    val productUiState: StateFlow<List<ProductBaseUiModel>>
        get() = _productUiState

    private val _productId = MutableStateFlow<DepartmentUiModel?>(null)

    init {
        observerProductChange()
    }

    fun observerProductChange() {
        viewModelScope.launch {
            _productId.filterNotNull().distinctUntilChangedBy { it }.collectLatest { it ->
                getProductByDepartment(it)
            }
        }
    }

    fun onSelectDepartment(id: String) {
        _uiState.update {
            it.mapIndexed { _, departmentUiModel ->
                when {
                    departmentUiModel.isSelected -> {
                        departmentUiModel.copy(isSelected = false)
                    }

                    departmentUiModel.id == id -> {
                        departmentUiModel.copy(isSelected = true)
                    }

                    else -> departmentUiModel
                }
            }
        }
        _productId.value = _uiState.value.find { it.id == id }
    }

    fun getDepartmentData() {
        launch {
            getDepartmentUseCase.run(Unit).map {
                it.apply {
                    it.first().isSelected = true
                }
            }.collectLatest {
                _uiState.emit(it)
                _productId.value = it.first()
            }
        }
    }

    fun getProductByDepartment(department: DepartmentUiModel) {
        launch {
            getProductsUseCase.run(department).collectLatest { productData ->
                _productUiState.emit(productData)
            }
        }
    }
}