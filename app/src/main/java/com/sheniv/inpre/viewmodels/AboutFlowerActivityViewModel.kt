package com.sheniv.inpre.viewmodels

import androidx.lifecycle.ViewModel
import com.sheniv.domain.model.Flower
import com.sheniv.domain.repository.usecases.ChangeFlowerInDataUseCase
import com.sheniv.domain.repository.usecases.GetBasketUseCase
import com.sheniv.domain.repository.usecases.PostAmountValueNullUseCase

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