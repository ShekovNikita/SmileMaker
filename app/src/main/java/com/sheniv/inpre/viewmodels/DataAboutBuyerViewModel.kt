package com.sheniv.inpre.viewmodels

import androidx.lifecycle.ViewModel
import com.sheniv.inpre.utilits.basket

class DataAboutBuyerViewModel : ViewModel() {

    fun getBasket() = basket.getBasket()

    fun getSumOfBasket() = basket.getSumOfBasket()
}