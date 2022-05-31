package com.example.inpre.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.AddToBasketUseCase
import com.example.domain.repository.usecases.GetBasketUseCase

class AboutFlowerActivityViewModel(
    private val addToBasketUseCase: AddToBasketUseCase,
    private val getBasketUseCase: GetBasketUseCase
) : ViewModel() {

    fun addToBasket(flower: Flower) {
        addToBasketUseCase.execute(flower)
    }

    fun getBasket(): ArrayList<Flower> {
        return getBasketUseCase.execute()
    }
}