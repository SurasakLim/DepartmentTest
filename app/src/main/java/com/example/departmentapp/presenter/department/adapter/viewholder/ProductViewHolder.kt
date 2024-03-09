package com.example.departmentapp.presenter.department.adapter.viewholder

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.departmentapp.common.loadScaleCenterCrop
import com.example.departmentapp.common.onClick
import com.example.departmentapp.databinding.LayoutProductBinding

class ProductViewHolder(
    private val binding: LayoutProductBinding,
    private val onClick: ((ProductBaseUiModel) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(departmentUiModel: ProductBaseUiModel.ProductUiModel) {
        with(binding) {
            ivProduct.loadScaleCenterCrop(departmentUiModel.imageUrl?.toUri())
            tvName.text = departmentUiModel.name
            tvDesc.text = departmentUiModel.desc
            tvPice.text = departmentUiModel.price

            root.onClick { onClick?.invoke(departmentUiModel) }
        }
    }

    companion object {
        operator fun invoke(
            viewBinding: LayoutProductBinding,
            onClick: ((ProductBaseUiModel) -> Unit)? = null
        ) = ProductViewHolder(viewBinding, onClick)
    }
}