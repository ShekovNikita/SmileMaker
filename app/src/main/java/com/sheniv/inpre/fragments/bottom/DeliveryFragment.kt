package com.sheniv.inpre.fragments.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentDeliveryBinding
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.bottomNavigationView
import com.sheniv.inpre.utilits.recyclerTop
import com.sheniv.inpre.viewmodels.DeliveryFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryFragment : BaseFragment<FragmentDeliveryBinding>() {

    private val viewModel by viewModel<DeliveryFragmentViewModel>()

    override fun onResume() {
        super.onResume()
        recyclerTop.beGone()
        bottomNavigationView.beGone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerTop.beVisible()
        bottomNavigationView.beVisible()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDeliveryBinding = FragmentDeliveryBinding.inflate(inflater, container, false)

    override fun FragmentDeliveryBinding.onBindView(savedInstanceState: Bundle?) {

        deliveryText.text = viewModel.getDeliveryText()
    }
}