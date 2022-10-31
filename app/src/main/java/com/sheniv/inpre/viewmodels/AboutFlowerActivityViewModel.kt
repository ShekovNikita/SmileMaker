package com.sheniv.inpre.viewmodels

import androidx.lifecycle.ViewModel
import com.sheniv.domain.repository.usecases.ChangeFlowerInDataUseCase
import com.sheniv.domain.repository.usecases.GetBasketUseCase
import com.sheniv.domain.repository.usecases.PostAmountValueNullUseCase
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.basket

class AboutFlowerActivityViewModel(
    private val changeFlowerInDataUseCase: ChangeFlowerInDataUseCase,
    private val getBasketUseCase: GetBasketUseCase,
    private val postAmountValueNullUseCase: PostAmountValueNullUseCase
) : ViewModel() {

    fun addToBasket(flower: FlowerMain) {
        basket.changeAmountInBasket(flower)
    }

    fun getBasket(): ArrayList<FlowerMain> {
        return basket.getBasket()
    }

    fun deleteFlower(flower: FlowerMain) {
        basket.deleteFromBasket(flower)
    }
}