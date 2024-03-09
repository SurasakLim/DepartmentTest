package com.example.departmentapp.presenter.department.adapter.viewholder

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.core.departmentModel.DepartmentUiModel
import com.example.departmentapp.R
import com.example.departmentapp.common.colorStateList
import com.example.departmentapp.common.loadScaleCenterCrop
import com.example.departmentapp.common.onClick
import com.example.departmentapp.databinding.LayoutDepartmentBinding

class DepartmentViewHolder(
    private val binding: LayoutDepartmentBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(departmentUiModel: DepartmentUiModel, onClick: (String) -> Unit) {
        with(binding) {
            vSelected.backgroundTintList = if (departmentUiModel.isSelected) {
                colorStateList(R.color.red_1)
            } else {
                colorStateList(R.color.black)
            }

            ivDepartment.loadScaleCenterCrop(departmentUiModel.imageUrl?.toUri())
            tvName.text = departmentUiModel.name
            root.onClick { onClick.invoke(departmentUiModel.id ?: "") }
        }
    }
}