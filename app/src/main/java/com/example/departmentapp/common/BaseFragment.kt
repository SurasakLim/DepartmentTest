package com.example.departmentapp.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewbinding.ViewBinding
import com.example.departmentapp.R
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    protected var viewBinding: ViewBinding? = null

    val disposables = CompositeDisposable()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onRequestFirstLoaded {
            (this as? BaseFragmentCallbacks)?.let { initViewModel() }
        }
    }

    private fun onRequestFirstLoaded(onSuccess: (() -> Unit)? = null) {
        onSuccess?.invoke()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (this) {
            is ViewBindingInflater<*> -> {
                bindingInflater.invoke(inflater, container, false)
                    .apply { viewBinding = this }
                    .root
            }

            else -> {
                inflater.inflate(0, container, false)
            }
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (this as? BaseFragmentCallbacks)?.let {
            setupView()
            bindViewEvents()
            bindViewModel()
        }
    }

    fun NavDirections.navigate(navOptions: NavOptions? = null) {
        try {
            activity?.runOnUiThread {
                findNavController().navigate(this, navOptions)
            }
        } catch (throwable: Throwable) {
            Log.e("NAV","${throwable.message}")
        }
    }

    fun NavDirections.navigate(popUpTo: Int) {
        val navOptions = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
            popUpTo(popUpTo) {
                inclusive = true
            }
        }
        navigate(navOptions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
        viewBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
