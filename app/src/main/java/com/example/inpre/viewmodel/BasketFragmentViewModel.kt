package com.example.inpre.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.ChangeAmountOfOneFlowerUseCase
import com.example.domain.repository.usecases.GetBasketUseCase

class BasketFragmentViewModel(
    private val getBasketUseCase: GetBasketUseCase,
    private val changeAmountOfOneFlowerUseCase: ChangeAmountOfOneFlowerUseCase
) : ViewModel() {

    fun getBasket() = getBasketUseCase.execute()

    fun getSummaInBasket(): Int {
        var summa = 0
        for (i in getBasket()) {
            summa += i.cost.toInt() * i.amount
        }
        return summa
    }

    fun changeAmount(flower: Flower): ArrayList<Flower>{
        println("---------------------------------------changeamountVIEWMODEL\n ${changeAmountOfOneFlowerUseCase.execute(flower)}")
        return changeAmountOfOneFlowerUseCase.execute(flower)
    }
}