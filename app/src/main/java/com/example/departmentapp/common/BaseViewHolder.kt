
package com.example.departmentapp.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View) :
    RecyclerView.ViewHolder(view) {

    abstract var item: T

    open fun bindItem(bindItem: T) {
        item = bindItem
    }

    open fun bind(bindItem: T) = Unit

}