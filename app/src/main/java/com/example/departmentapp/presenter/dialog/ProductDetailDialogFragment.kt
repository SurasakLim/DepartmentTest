package com.example.departmentapp.presenter.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core.departmentModel.ProductBaseUiModel
import com.example.departmentapp.common.BaseDialogFragment
import com.example.departmentapp.databinding.DialogProductDetailBinding

class ProductDetailDialogFragment : BaseDialogFragment() {

    private val binding by lazy {
        DialogProductDetailBinding.inflate(layoutInflater)
    }

    private val args by navArgs<ProductDetailDialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun initViewProperties() {
        if (args.productDetail is ProductBaseUiModel.ProductUiModel) {
            (args.productDetail as ProductBaseUiModel.ProductUiModel).let {
                with(binding) {
                    tvDesc.text = it.desc
                }
            }
        }
    }

    override fun initListener() {
        binding.btClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
