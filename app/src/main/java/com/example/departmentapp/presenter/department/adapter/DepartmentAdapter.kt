package com.example.departmentapp.presenter.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.departmentModel.DepartmentUiModel
import com.example.departmentapp.common.DiffUpdateAdapter
import com.example.departmentapp.databinding.LayoutDepartmentBinding
import com.example.departmentapp.presenter.department.adapter.viewholder.DepartmentViewHolder
import kotlin.properties.Delegates

class DepartmentAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<DepartmentViewHolder>(), DiffUpdateAdapter {

    private var data: List<DepartmentUiModel> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(
            old,
            new,
            { oldItem, newItem -> oldItem == newItem },
            { oldItem, newItem -> oldItem.isSelected == newItem.isSelected }

        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        return DepartmentViewHolder(
            LayoutDepartmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bind(data[position], onClick)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(data: List<DepartmentUiModel>) {
        this.data = data
    }

    companion object {
        const val VIEW_TYPE = 1000
    }
}