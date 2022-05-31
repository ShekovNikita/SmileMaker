package com.example.inpre.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.repository.usecases.GetBasketUseCase
import com.example.domain.repository.usecases.GetSumOfBasketUseCase

class DataAboutBuyerViewModel(
    private val getBasketUseCase: GetBasketUseCase,
    private val getSumOfBasketUseCase: GetSumOfBasketUseCase
) : ViewModel() {

    fun getBasket() = getBasketUseCase.execute()

    fun getSumOfBasket() = getSumOfBasketUseCase.execute()
}