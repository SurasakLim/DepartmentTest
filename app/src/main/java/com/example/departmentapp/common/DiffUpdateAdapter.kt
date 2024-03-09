package com.example.departmentapp.common

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.departmentapp.R

interface DiffUpdateAdapter {

    fun <T> RecyclerView.Adapter<*>.autoNotify(
        oldList: List<T>,
        newList: List<T>,
        compareItem: (T, T) -> Boolean,
        compareContent: ((T, T) -> Boolean)? = null
    ) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compareItem(oldList[oldItemPosition], newList[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return if (compareContent != null) {
                    compareContent(oldList[oldItemPosition], newList[newItemPosition])
                } else {
                    oldList[oldItemPosition] == newList[newItemPosition]
                }
            }

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size
        })

        diff.dispatchUpdatesTo(this)
    }
}

fun RecyclerView.ViewHolder.string(@StringRes id: Int?): String =
    itemView.context.getString(id ?: R.string.not_found)

fun RecyclerView.ViewHolder.colorStateList(@ColorRes id: Int?) = if (id != null) {
    ContextCompat.getColorStateList(itemView.context, id)
} else {
    ColorStateList.valueOf(Color.WHITE)
}
