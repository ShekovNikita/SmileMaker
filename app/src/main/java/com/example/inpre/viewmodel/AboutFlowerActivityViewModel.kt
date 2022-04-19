package com.example.inpre.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.AddToBasketUseCase

class AboutFlowerActivityViewModel(private val addToBasketUseCase: AddToBasketUseCase) :
    ViewModel() {

    fun addToBasket(flower: Flower) {
        addToBasketUseCase.execute(flower)
    }
}