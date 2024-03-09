package com.example.departmentapp.presenter.department.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.departmentapp.R
import com.example.departmentapp.common.string
import com.example.departmentapp.databinding.LayoutProductHeaderBinding

class ProductHeaderViewHolder(
    private val binding: LayoutProductHeaderBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bindItem: ProductBaseUiModel.ProductHeaderUiModel) {
        with(binding) {
            tvName.text = string(R.string.product_list).format(bindItem.name)
        }
    }

    companion object {
        operator fun invoke(
            viewBinding: LayoutProductHeaderBinding,
        ) = ProductHeaderViewHolder(viewBinding)
    }
}