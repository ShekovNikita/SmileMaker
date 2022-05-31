package com.example.inpre.fragments.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentDeliveryBinding
import com.example.inpre.viewmodels.DeliveryFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryFragment : BaseFragment<FragmentDeliveryBinding>() {

    private val viewModel by viewModel<DeliveryFragmentViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDeliveryBinding = FragmentDeliveryBinding.inflate(inflater, container, false)

    override fun FragmentDeliveryBinding.onBindView(savedInstanceState: Bundle?) {

        deliveryText.text = viewModel.getDeliveryText()
    }
}