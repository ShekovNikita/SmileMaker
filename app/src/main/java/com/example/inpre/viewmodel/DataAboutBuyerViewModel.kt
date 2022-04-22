package com.example.inpre.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.repository.usecases.GetBasketUseCase

class DataAboutBuyerViewModel(private val getBasketUseCase: GetBasketUseCase): ViewModel() {

    fun getBasket() = getBasketUseCase.execute()
}