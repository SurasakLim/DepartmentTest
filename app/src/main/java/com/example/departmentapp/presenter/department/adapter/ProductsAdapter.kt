package com.example.departmentapp.presenter.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.departmentapp.common.DiffUpdateAdapter
import com.example.departmentapp.databinding.LayoutProductBinding
import com.example.departmentapp.databinding.LayoutProductHeaderBinding
import com.example.departmentapp.presenter.department.adapter.viewholder.ProductHeaderViewHolder
import com.example.departmentapp.presenter.department.adapter.viewholder.ProductViewHolder
import kotlin.properties.Delegates

class ProductsAdapter(private val onClick: (ProductBaseUiModel) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffUpdateAdapter {

    private var data: List<ProductBaseUiModel> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(
            old,
            new,
            { oldItem, newItem -> oldItem == newItem }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                LayoutProductHeaderBinding.inflate(
                    parent.getLayoutInflater(),
                    parent,
                    false
                ).let {
                    ProductHeaderViewHolder.invoke(it)
                }
            }

            CONTENT -> {
                LayoutProductBinding.inflate(
                    parent.getLayoutInflater(),
                    parent,
                    false
                ).let {
                    ProductViewHolder.invoke(it, onClick)
                }
            }

            else -> throw IllegalStateException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = data[position]
        when (holder) {
            is ProductViewHolder -> holder.bind(uiModel as ProductBaseUiModel.ProductUiModel)
            is ProductHeaderViewHolder -> holder.bind(uiModel as ProductBaseUiModel.ProductHeaderUiModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is ProductBaseUiModel.ProductHeaderUiModel -> HEADER
            is ProductBaseUiModel.ProductUiModel -> CONTENT
            else -> CONTENT
        }
    }

    override fun getItemCount(): Int = data.size

    fun updateData(data: List<ProductBaseUiModel>) {
        this.data = data
    }

    private fun ViewGroup.getLayoutInflater(): LayoutInflater = LayoutInflater.from(context)

    companion object {
        const val HEADER = 1
        const val CONTENT = 2
    }
}
