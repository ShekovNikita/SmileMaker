package com.example.inpre.fragments

import android.os.BaseBundle
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentBasketBinding

class BasketFragment : BaseFragment<FragmentBasketBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBasketBinding = FragmentBasketBinding.inflate(inflater, container, false)

    override fun FragmentBasketBinding.onBindView(savedInstanceState: Bundle?) {
    }

}