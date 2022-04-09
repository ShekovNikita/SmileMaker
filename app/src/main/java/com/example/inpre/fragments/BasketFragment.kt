package com.example.inpre.fragments

import android.os.BaseBundle
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.repository.usecases.GetBasketUseCase
import com.example.inpre.adapter.BasketAdapter
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentBasketBinding
import com.example.inpre.viewmodel.BasketFragmentViewModel
import com.example.inpre.viewmodel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BasketFragment : BaseFragment<FragmentBasketBinding>() {

    private val viewModelMain by viewModel<BasketFragmentViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBasketBinding = FragmentBasketBinding.inflate(inflater, container, false)

    override fun FragmentBasketBinding.onBindView(savedInstanceState: Bundle?) {

        recyclerBasket.adapter = BasketAdapter(viewModelMain.getBasket())

        println(viewModelMain.getBasket())
    }

}