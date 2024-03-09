package com.example.departmentapp.common

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.departmentapp.R

fun ImageView.loadScaleCenterCrop(uri: Uri?) {
    val error = Glide.with(context)
        .load(R.drawable.ic_image)
        .transform(CenterCrop())
    Glide.with(context)
        .load(uri)
        .placeholder(R.drawable.ic_image)
        .transform(RoundedCorners(8))
        .error(error)
        .into(this)
}