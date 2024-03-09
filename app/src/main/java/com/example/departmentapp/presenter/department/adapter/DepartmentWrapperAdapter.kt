package com.example.departmentapp.presenter.department.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.departmentapp.databinding.ViewDepartmentHorizontalBinding
import com.example.departmentapp.presenter.department.adapter.viewholder.DepartmentWrapperViewHolder

class DepartmentWrapperAdapter(
    private val adapter: DepartmentAdapter
) : RecyclerView.Adapter<DepartmentWrapperViewHolder>() {
    private var lastScrollX = 0

    companion object {
        private const val KEY_SCROLL_X = "horizontal.wrapper.adapter.key_scroll_x"
        const val VIEW_TYPE = 1001
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentWrapperViewHolder {
        return DepartmentWrapperViewHolder(
            ViewDepartmentHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    override fun onBindViewHolder(holder: DepartmentWrapperViewHolder, position: Int) {
        holder.bind(adapter, lastScrollX) { x ->
            lastScrollX = x
        }
    }

    override fun getItemCount(): Int = 1

    fun onSaveState(outState: Bundle) {
        outState.putInt(KEY_SCROLL_X, lastScrollX)
    }

    fun onRestoreState(savedState: Bundle) {
        lastScrollX = savedState.getInt(KEY_SCROLL_X)
    }
}