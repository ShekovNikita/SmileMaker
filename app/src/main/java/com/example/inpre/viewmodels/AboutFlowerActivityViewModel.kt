package com.example.inpre.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.ChangeFlowerInDataUseCase
import com.example.domain.repository.usecases.GetBasketUseCase
import com.example.domain.repository.usecases.PostAmountValueNullUseCase

class AboutFlowerActivityViewModel(
    private val changeFlowerInDataUseCase: ChangeFlowerInDataUseCase,
    private val getBasketUseCase: GetBasketUseCase,
    private val postAmountValueNullUseCase: PostAmountValueNullUseCase
) : ViewModel() {

    fun addToBasket(flower: Flower) {
        changeFlowerInDataUseCase.execute(flower)
    }

    fun getBasket(): ArrayList<Flower> {
        return getBasketUseCase.execute()
    }

    fun deleteFlower(flower: Flower) {
        postAmountValueNullUseCase.execute(flower)
    }
}