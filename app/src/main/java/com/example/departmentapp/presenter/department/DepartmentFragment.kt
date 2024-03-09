package com.example.departmentapp.presenter.department

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.departmentapp.common.BaseFragment
import com.example.departmentapp.common.BaseFragmentCallbacks
import com.example.departmentapp.databinding.DepartmentFragmentBinding
import com.example.departmentapp.presenter.department.adapter.DepartmentAdapter
import com.example.departmentapp.presenter.department.adapter.DepartmentWrapperAdapter
import com.example.departmentapp.presenter.department.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DepartmentFragment : BaseFragment(), BaseFragmentCallbacks {

    private val binding by lazy {
        DepartmentFragmentBinding.inflate(layoutInflater)
    }

    private val directions = DepartmentFragmentDirections

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    private val viewModel: DepartmentViewModel by viewModels()

    override fun initViewModel() {
        viewModel.getDepartmentData()
    }

    private val departmentAdapter: DepartmentAdapter by lazy {
        DepartmentAdapter { onDepartmentClick(it) }
    }

    private val productAdapter: ProductsAdapter by lazy {
        ProductsAdapter { onProductClick(it) }
    }

    private fun onProductClick(it: ProductBaseUiModel) {
        directions.toProductDetailDialog(it).navigate()
    }

    private fun onDepartmentClick(it: String) {
        viewModel.onSelectDepartment(it)
    }

    private val departmentWrapperAdapter: DepartmentWrapperAdapter by lazy {
        DepartmentWrapperAdapter(departmentAdapter)
    }

    private val concatAdapter: ConcatAdapter by lazy {
        val config = ConcatAdapter.Config.Builder().apply {
            setIsolateViewTypes(false)
        }.build()
        ConcatAdapter(config, departmentWrapperAdapter, productAdapter)
    }

    override fun setupView() {
        val layoutManager = GridLayoutManager(requireContext(), 12)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (concatAdapter.getItemViewType(position)) {
                    ProductsAdapter.HEADER -> 12
                    ProductsAdapter.CONTENT -> 6
                    DepartmentWrapperAdapter.VIEW_TYPE -> 12
                    else -> 12
                }
            }
        }
        binding.rvContent.layoutManager = layoutManager
        binding.rvContent.adapter = concatAdapter
    }

    override fun bindViewEvents() {
    }

    override fun bindViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                departmentAdapter.updateData(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productUiState.collectLatest {
                productAdapter.updateData(it)
            }
        }
    }
}