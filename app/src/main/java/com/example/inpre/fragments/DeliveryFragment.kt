package com.example.inpre.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentDeliveryBinding

class DeliveryFragment : BaseFragment<FragmentDeliveryBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDeliveryBinding = FragmentDeliveryBinding.inflate(inflater, container, false)

    override fun FragmentDeliveryBinding.onBindView(savedInstanceState: Bundle?) {

    }

}