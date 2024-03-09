package com.example.departmentapp.presenter.department.adapter.viewholder

import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.departmentapp.databinding.ViewDepartmentHorizontalBinding
import com.example.departmentapp.presenter.department.adapter.DepartmentAdapter

class DepartmentWrapperViewHolder(
    private val binding: ViewDepartmentHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(adapter: DepartmentAdapter, lastScrollX: Int, onScrolled: (Int) -> Unit) {
        val context = binding.root.context
        with(binding.recyclerView) {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
            doOnPreDraw {
                scrollBy(lastScrollX, 0)
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    onScrolled(recyclerView.computeHorizontalScrollOffset())
                }
            })
        }
    }
}
