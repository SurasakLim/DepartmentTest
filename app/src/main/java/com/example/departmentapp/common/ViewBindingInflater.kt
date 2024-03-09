package com.example.departmentapp.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface ViewBindingInflater<VB : ViewBinding> {

    val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    val binding: VB
}
