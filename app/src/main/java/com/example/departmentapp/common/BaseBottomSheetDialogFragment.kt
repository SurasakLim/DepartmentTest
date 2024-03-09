package com.example.departmentapp.common

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.departmentapp.R
import io.reactivex.disposables.CompositeDisposable

abstract class BaseDialogFragment : DialogFragment() {

    val compositeDisposable = CompositeDisposable()

    fun backPress() {
        findNavController().popBackStack()
    }

    open fun initConsumer() = Unit

    open fun initListener() = Unit

    open fun initObserver() = Unit

    open fun initViewProperties() = Unit

    fun NavDirections.navigate() {
        findNavController().navigate(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogTheme)
        initConsumer()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewProperties()
        initListener()
        initObserver()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}