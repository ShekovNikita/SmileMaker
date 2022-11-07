package com.sheniv.inpre.viewmodels

import androidx.lifecycle.ViewModel
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.basket

class AboutFlowerActivityViewModel() : ViewModel() {

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